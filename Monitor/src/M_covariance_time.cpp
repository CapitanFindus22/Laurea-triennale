#include "main.hpp"

void MCT()
{
    Con2DB db(IP, PORT_DB, USERNAME, PASSWORD, DB_NAME);
    redisContext *c = redisConnect(IP, PORT);
    redisReply *r;
    return;
}