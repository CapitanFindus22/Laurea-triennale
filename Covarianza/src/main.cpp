#include "main.hpp"

extern std::atomic<size_t> done;
extern std::atomic<bool> running;
extern size_t windowLength;

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

    std::string baseName = "STREAM_";
    size_t i;

    initStreams(c,"INFOSTREAM","covariance");
    initStreams(c,"WINDOW","covariance");

    size_t num_stream = std::stoi(ReadInfo(c,"INFOSTREAM"));
    int id = std::stoi(ReadInfo(c,"INFOSTREAM"));
    windowLength = std::stoi(ReadInfo(c,"WINDOW"));

    std::cout << "Sessione n°" << id << " Numero di stream: " << num_stream << std::endl;

    std::cout << "La finestra contiene " << windowLength << " elementi" << std::endl;

    // Stuff for the threads
    std::thread threads[num_stream];
    std::string names[num_stream];
    std::string windows[num_stream];
    std::vector<std::vector<float>> values(num_stream);

    // Initialize the streams and generate the names
    for (i = 0; i < num_stream; i++)
    {
        names[i] = baseName + std::to_string(i);
        initStreams(c,names[i].c_str(),"covariance");

    }

    for (i = 0; i < num_stream; i++)
    {
        threads[i] = std::thread(ReadMessage, names[i], std::ref(windows[i]));
    }

    while(1)
    {
        if(done.load() >= num_stream)
        { 

            for (i = 0; i < num_stream; i++)
            {
                String2Float(windows[i],std::ref(values[i]));
            }

            Covariance(std::ref(db1),std::ref(values),id);
            done = 0;

        }

    }

    running = false;

    for (std::thread &t : threads)
    {
        t.join();
    }

    // Close the connection
    redisFree(c);

    return 0;
}
