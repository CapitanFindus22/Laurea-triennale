#include "main.hpp"

extern std::atomic<size_t> done;
extern std::atomic<bool> running;
extern size_t windowLength;

// Read a message from the stream named Streamname
void ReadMessage(std::string StreamName, int id, std::deque<float> &arr, std::string &result)
{
  bool isEmpty = true;
  float mean;
  float val;
  redisContext *c = redisConnect(IP, PORT);
  redisReply *r;

  Con2DB db(IP, PORT_DB, USERNAME, PASSWORD, DB_NAME);

  while (1)
  {

    // Read
    r = RedisCommand(c, "XREADGROUP GROUP mean user BLOCK %d COUNT 1 NOACK STREAMS %s >",
                     BLOCK, StreamName.c_str());

    assertReplyType(c, r, REDIS_REPLY_ARRAY);
    val = std::stof(r->element[0]->element[1]->element[0]->element[1]->element[1]->str);
    freeReplyObject(r);

    arr.push_back(val);

    mean = Mean(arr);

    if (!isEmpty)
    {
      Alert(std::ref(db), StreamName, mean, id);
    }

    if (arr.size() == windowLength)
    {
      log2db(std::ref(db), mean, StreamName, id);

      isEmpty = false;

      result.clear();

      for (float &v : arr)
      {
        result += std::to_string(v) + ',';
      }

      result += std::to_string(mean);

      done.fetch_add(1, std::memory_order_relaxed);
      while (done.load() > 0)
      {
        std::this_thread::yield();
      }

      arr.pop_front();
    }
  }
}

void SendMessage(redisContext *c, const char *arr, std::string StreamName)
{

    redisReply *r = RedisCommand(c, "XADD %s * value %s", StreamName.c_str(), arr);
    assertReplyType(c, r, REDIS_REPLY_STRING);
    freeReplyObject(r);
  
}