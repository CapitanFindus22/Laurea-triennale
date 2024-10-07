#include "main2.hpp"

extern std::atomic<bool> running;

int main()
{

#if (DEBUG > 0)
    setvbuf(stdout, (char *)NULL, _IONBF, 0);
    setvbuf(stderr, (char *)NULL, _IONBF, 0);
#endif

    // Connection to DB
    Con2DB db1("localhost", "5432", "trafficlight", "47002", "logdb_trafficlight");
    PGresult *res;

    // Connection to Redis
    redisContext *c = redisConnect(IP, PORT);

    std::string baseName = "STREAM";
    size_t i;

    // Stream0 is used to receive various information, in this case the number of streams
    initStreams(c, "INFOSTREAM");
    redisReply *r = RedisCommand(c, "XREADGROUP GROUP reader r1 BLOCK %d COUNT 1 NOACK STREAMS %s >",
                                 BLOCK, "INFOSTREAM");

    size_t num_stream = std::stoi(r->element[0]->element[1]->element[0]->element[1]->element[1]->str);

    // The names of the streams
    std::string names[num_stream];

    // Stuff for the threads
    const size_t numThreads = num_stream;
    std::vector<std::thread> threads;

    // Initialize the streams and generate the names
    for (i = 0; i < num_stream; i++)
    {
        names[i] = GenerateStreamName(baseName, i);
        initStreams(c, names[i].c_str());
    }

    for (i = 0; i < numThreads; i++)
    {
        threads.push_back(std::thread(ReadMessage, c, names[i],db1));
    }
    // std::cout << log2db(db1,ReadMessage(c,GenerateStreamName(baseName, i)));

    sleep(100000000000000000);
    running = false;

    for (std::thread &t : threads)
    {
        t.join();
    }

    /*TODO

        - Controllare makefile
        - Funzione per calcolare covarianza
        - Implementare i 2 monitor
        - Creare roba per DB
        - Modificare log2db
        - Implementare alert

    */

    // Close the connection
    redisFree(c);

    return 0;
}
