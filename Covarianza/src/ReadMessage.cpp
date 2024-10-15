#include "main.hpp"

extern std::atomic<size_t> done;
extern std::atomic<bool> running;
extern size_t windowLength;

// Read a message from the stream named Streamname
void ReadMessage(std::string StreamName, std::string& arr)
{

  redisContext *c = redisConnect(IP, PORT);
  redisReply *r;

  arr.clear();

  while (1)
  {

    // Read
    r = RedisCommand(c, "XREADGROUP GROUP covariance user BLOCK %d COUNT 1 NOACK STREAMS %s >",
                      BLOCK, StreamName.c_str());

    assertReplyType(c, r, REDIS_REPLY_ARRAY);
    arr = r->element[0]->element[1]->element[0]->element[1]->element[1]->str;
    freeReplyObject(r);

    done.fetch_add(1,std::memory_order_relaxed);
    while(done.load()>0){std::this_thread::yield();}

  }
}

std::string ReadInfo(redisContext *c, std::string streamName)
{
    // Stream0 is used to receive various information, in this case the number of streams
    redisReply *r = RedisCommand(c, "XREADGROUP GROUP covariance user BLOCK %d COUNT 1 STREAMS %s >",
                                 BLOCK, streamName.c_str());
    assertReplyType(c,r,REDIS_REPLY_ARRAY);
    std::string result = r->element[0]->element[1]->element[0]->element[1]->element[1]->str;
    std::string ID = r->element[0]->element[1]->element[0]->element[0]->str;
    freeReplyObject(r);

    r = RedisCommand(c, "XACK %s mean %s",streamName.c_str(),ID.c_str());
    assertReply(c,r);
    freeReplyObject(r);

    return result;
}