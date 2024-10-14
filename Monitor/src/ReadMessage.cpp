#include "main.hpp"

// Read a message from the stream named Streamname
std::string ReadMessage(redisContext *c, std::string StreamName)
{
  std::string result;
  redisReply *r;

  // Read
  r = RedisCommand(c, "XREADGROUP GROUP monitor monitor BLOCK %d COUNT 1 NOACK STREAMS %s >",
                    BLOCK, StreamName.c_str());

  assertReplyType(c, r, REDIS_REPLY_ARRAY);
  result = r->element[0]->element[1]->element[0]->element[1]->element[1]->str;
  freeReplyObject(r);
  return result;
}
