#include "main.hpp"

extern std::atomic<bool> running;
extern size_t windowLength;
std::mutex redisMutex;

// Read a message from the stream named Streamname
void ReadMessage(redisContext *c, std::string StreamName, Con2DB db, int id)
{
  bool isEmpty = true;
  float mean;
  float val;
  std::string strValue;
  redisReply *r;
  std::deque<float> arr;

  while (running)
  {

    /*Since this function is used for the threads and redisContext* c
      isn't thread safe the mutex is essential
    */
    {
      std::lock_guard<std::mutex> lock(redisMutex);

      // Read
      r = RedisCommand(c, "XREADGROUP GROUP reader r1 BLOCK %d COUNT 1 STREAMS %s >",
                       BLOCK, StreamName.c_str());

      assertReplyType(c, r, REDIS_REPLY_ARRAY);
    }

    strValue = r->element[0]->element[1]->element[0]->element[1]->element[1]->str;

    freeReplyObject(r);

    val = std::stof(strValue);

    arr.push_back(val);

    mean = Mean(arr);

    if (arr.size() > windowLength)
    {
      arr.pop_front();
      isEmpty = false;
    }

    if (!isEmpty)
    {
      monitor(db, StreamName, mean, id);
    }

    if (arr.size() == windowLength)
    {
      log2db(db, mean, StreamName, id);
    }
  }
}
