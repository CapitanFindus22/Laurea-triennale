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

    // Stream0 is used to receive various information, in this case the number of streams
    initStreams(c, "INFOSTREAM");
    redisReply *r = RedisCommand(c, "XREADGROUP GROUP reader r2 BLOCK %d COUNT 1 NOACK STREAMS %s >",
                                 BLOCK, "INFOSTREAM");
    size_t num_stream = std::stoi(r->element[0]->element[1]->element[0]->element[1]->element[1]->str);

    freeReplyObject(r);

    r = RedisCommand(c, "XREADGROUP GROUP reader r2 BLOCK %d COUNT 1 NOACK STREAMS %s >",
                     BLOCK, "INFOSTREAM");
    int id = std::stoi(r->element[0]->element[1]->element[0]->element[1]->element[1]->str);

    freeReplyObject(r);

    // Stuff for the threads
    std::thread threads[num_stream];
    std::string names[num_stream];
    std::deque<float> windows[num_stream];

    // Initialize the streams and generate the names
    for (i = 0; i < num_stream; i++)
    {
        names[i] = baseName + std::to_string(i);
        initStreams(c, names[i].c_str());
    }

    for (i = 0; i < num_stream; i++)
    {
        threads[i] = std::thread(ReadMessage, c, names[i], db1, id, std::ref(windows[i]));
    }

    bool asd = true;

    while(asd)
    {
        if(done.load() >= num_stream)
        { 
            Covariance(db1,windows,num_stream,id);
            done = 0;
            asd = false;
        }

    }

    running = false;

    for (std::thread &t : threads)
    {
        t.join();
    }

    /*TODO

        - Implementare i 2 monitor
        - Implementare alert

    */

    // Close the connection
    redisFree(c);

    return 0;
}
