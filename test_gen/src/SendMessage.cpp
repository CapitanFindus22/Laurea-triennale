#include "main.hpp"

void SendMessage(redisContext *c, float val, std::string StreamName)
{
    redisReply *r = RedisCommand(c, "XADD %s * value %f", StreamName.c_str(), val);
    assertReplyType(c, r, REDIS_REPLY_STRING);
    std::cout << StreamName << " -> " << val << std::endl;
    freeReplyObject(r);
}