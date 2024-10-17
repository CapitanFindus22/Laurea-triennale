#ifndef con2redis_h
#define con2redis_h

#include <string>
#include <stdio.h>

extern "C"
{
#include <hiredis/hiredis.h>
}

#define dbg_log(fmt, ...)                                                      \
    do                                                                         \
    {                                                                          \
        fprintf(stderr, "%s:%d : " fmt "\n", __FILE__, __LINE__, __VA_ARGS__); \
    } while (0);

#define dbg_abort(fmt, ...)        \
    do                             \
    {                              \
        dbg_log(fmt, __VA_ARGS__); \
        exit(-1);                  \
    } while (0)

#define RedisCommand(fmt, ...) \
    (redisReply *)redisCommand(fmt, __VA_ARGS__)

#define BLOCK 10000000

#define ISTREAM "INFOSTREAM"

void assertReplyType(redisContext *c, redisReply *r, int type);

void assertReply(redisContext *c, redisReply *r);

void dumpReply(redisReply *r, int indent);

void initStreams(redisContext *c, const char *, const char *);

void SendMessage(redisContext *c, std::string arr, std::string StreamName);

std::string ReadMessage(redisContext *c, std::string StreamName, std::string GroupName, std::string UserName);

#endif