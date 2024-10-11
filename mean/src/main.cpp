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

    std::string baseName = "STREAM";
    size_t i;

    sleep(1);

    // Stream0 is used to receive various information, in this case the number of streams
    redisReply *r = RedisCommand(c, "XREADGROUP GROUP reader r1 BLOCK %d COUNT 1 NOACK STREAMS %s >",
                                 BLOCK, "INFOSTREAM");
    size_t num_stream = std::stoi(r->element[0]->element[1]->element[0]->element[1]->element[1]->str);

    freeReplyObject(r);

    r = RedisCommand(c, "XREADGROUP GROUP reader r1 BLOCK %d COUNT 1 NOACK STREAMS %s >",
                     BLOCK, "INFOSTREAM");
    int id = std::stoi(r->element[0]->element[1]->element[0]->element[1]->element[1]->str);

    freeReplyObject(r);

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
        r = RedisCommand(c, "DEL %s", names2[i].c_str());
        initStreams(c,names2[i].c_str());
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
