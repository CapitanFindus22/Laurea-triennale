#include "main.hpp"

extern std::atomic<size_t> done;
extern std::atomic<bool> running;

int main()
{

#if (DEBUG > 0)
    setvbuf(stdout, (char *)NULL, _IONBF, 0);
    setvbuf(stderr, (char *)NULL, _IONBF, 0);
#endif

    // Connection to DB
    Con2DB db1(IP, PORT_DB, USERNAME, PASSWORD, DB_NAME);

    // Connection to Redis
    redisContext *c = redisConnect(IP, PORT);
    redisReply *r;

    std::string baseName = "STREAM";
    std::string ID;
    size_t i;

    sleep(2);

    initStreams(c,"INFOSTREAM","mean");

    // Stream0 is used to receive various information, in this case the number of streams
    r = RedisCommand(c, "XREADGROUP GROUP mean user BLOCK %d COUNT 1 STREAMS %s >",
                                 BLOCK, "INFOSTREAM");
    assertReplyType(c,r,REDIS_REPLY_ARRAY);
    size_t num_stream = std::stoi(r->element[0]->element[1]->element[0]->element[1]->element[1]->str);
    ID = r->element[0]->element[1]->element[0]->element[0]->str;
    freeReplyObject(r);

    r = RedisCommand(c, "XACK %s mean %s",
                                 "INFOSTREAM",ID.c_str());
    assertReply(c,r);
    freeReplyObject(r);

    r = RedisCommand(c, "XREADGROUP GROUP mean user BLOCK %d COUNT 1 STREAMS %s >",
                     BLOCK, "INFOSTREAM");
    assertReplyType(c,r,REDIS_REPLY_ARRAY);
    int id = std::stoi(r->element[0]->element[1]->element[0]->element[1]->element[1]->str);
    ID = r->element[0]->element[1]->element[0]->element[0]->str;
    freeReplyObject(r);

    r = RedisCommand(c, "XACK %s mean %s",
                                 "INFOSTREAM",ID.c_str());
    assertReply(c,r);
    freeReplyObject(r);

    std::cout << num_stream << " " << id << std::endl;

    // Stuff for the threads
    std::thread threads[num_stream];
    std::string names[num_stream];
    std::string names2[num_stream];
    std::deque<float> windows[num_stream];
    std::string toSend[num_stream];

    // Initialize the streams and generate the names
    for (i = 0; i < num_stream; i++)
    {
        names[i] = baseName + std::to_string(i);
        names2[i] = baseName + '_' + std::to_string(i);
        RedisCommand(c, "XTRIM %s 0", names2[i].c_str());
        initStreams(c,names[i].c_str(),"mean");
    }

    for (i = 0; i < num_stream; i++)
    {
        threads[i] = std::thread(ReadMessage, c, names[i], db1, id, std::ref(windows[i]),std::ref(toSend[i]));
    }

    while(1)
    {
        if(done.load() >= num_stream)
        { 
            for (i = 0; i < num_stream; i++)
            {   
                std::cout << names2[i] << ":" << toSend[i] << std::endl;
                SendMessage(c,toSend[i].c_str(),names2[i]);

            }
            done = 0;

        }

    }

    for (std::thread &t : threads)
    {
        t.join();
    }

    // Close the connection
    redisFree(c);

    return 0;
}
