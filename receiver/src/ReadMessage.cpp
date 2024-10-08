#include "main.hpp"

extern std::atomic<bool> running;
extern size_t windowLength;
std::mutex redisMutex;

// Read a message from the stream named Streamname
void ReadMessage(redisContext *c, std::string StreamName, Con2DB db)
{

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

    if(arr.size() > windowLength) 
    {
      arr.pop_front();
    }

    arr.push_back(val);
    if(arr.size() == windowLength) {log2db(db,Mean(arr),StreamName);}
    
  }
}
