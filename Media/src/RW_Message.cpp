#include "main.hpp"

// Read a message from the stream named Streamname
std::string ReadMessage(redisContext *c, std::string StreamName)
{
    redisReply *r;
    std::string result;
    std::string ID;

    // Read
    r = RedisCommand(c, "XREADGROUP GROUP mean user BLOCK %d COUNT 1 STREAMS %s >",
                     BLOCK, StreamName.c_str());

    assertReplyType(c, r, REDIS_REPLY_ARRAY);
    result = r->element[0]->element[1]->element[0]->element[1]->element[1]->str;
    ID = r->element[0]->element[1]->element[0]->element[0]->str;
    freeReplyObject(r);

    r = RedisCommand(c, "XACK %s mean %s", "INFOSTREAM", ID.c_str());
    assertReply(c, r);
    freeReplyObject(r);

    return result;
}

// Send a message to StreamName
void SendMessage(redisContext *c, std::string arr, std::string StreamName)
{

    redisReply *r = RedisCommand(c, "XADD %s * value %s", StreamName.c_str(), arr.c_str());
    assertReplyType(c, r, REDIS_REPLY_STRING);
    freeReplyObject(r);
}