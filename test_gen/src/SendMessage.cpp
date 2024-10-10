#include "main.hpp"

std::mutex redisMutex;

void SendMessage(redisContext *c, size_t val, std::string StreamName)
{

    redisReply *r = RedisCommand(c, "XADD %s * value %d", StreamName.c_str(), val);
    assertReplyType(c, r, REDIS_REPLY_STRING);
    freeReplyObject(r);
}

void SendMessage(redisContext *c, float val, std::string StreamName)
{
    {

    std::lock_guard<std::mutex> lock(redisMutex);
    std::cout << StreamName << " " << val << std::endl;
    redisReply *r = RedisCommand(c, "XADD %s * value %f", StreamName.c_str(), val);
    assertReplyType(c, r, REDIS_REPLY_STRING);
    dumpReply(r,0);
    freeReplyObject(r);
    
    }
}