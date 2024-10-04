#include "main2.hpp"

int main()
{

#if (DEBUG > 0)
    setvbuf(stdout, (char *)NULL, _IONBF, 0);
    setvbuf(stderr, (char *)NULL, _IONBF, 0);
#endif

    Con2DB db1("localhost", "5432", "trafficlight", "47002", "logdb_trafficlight");
    PGresult *res;
 
    redisContext *c = redisConnect(IP, PORT);
    redisReply *r;

    float val;
    int block = 1000000000;

    initStreams(c, "STREAM2");

    while(1) {

    r = RedisCommand(c, "XREAD BLOCK %d COUNT 1 STREAMS %s $", 
                         block, "STREAM2");

    assertReply(c, r);

   /*  redisReply* messages = r->element[1];
    val = std::stof(messages->element[1]->element[1]->str); */

    std::cout << r->element[0]->element[1]->element[0]->element[1]->element[1]->str << "\n";

    //std::cout << r->element[0]->element[1]->element[0]->str << "\n";

    //dumpReply(r, 0);
    freeReplyObject(r);

    //log2db(db1,val);

    }

    redisFree(c);

    // deque

    return 0;
}
