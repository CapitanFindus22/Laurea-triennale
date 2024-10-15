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

    // Connection to Redis
    redisContext *c = redisConnect(IP, PORT);

    RedisCommand(c,"XTRIM WINDOW MAXLEN 0");
    windowLength = ChooseSize();
    std::cout << "La finestra contiene " << windowLength << " elementi" << std::endl;
    SendMessage(c,std::to_string(windowLength),"WINDOW");

    std::string baseName = "STREAM";
    size_t i;

    initStreams(c,"INFOSTREAM","mean");

    size_t num_stream = std::stoi(ReadInfo(c));
    int id = std::stoi(ReadInfo(c));

    std::cout << "Sessione n°" << id << " Numero di stream: " << num_stream << std::endl;

    // Stuff for the threads
    std::thread threads[num_stream];
    std::string streamNameIN[num_stream];
    std::string streamNameOUT[num_stream];
    std::deque<float> windows[num_stream];
    std::string toSend[num_stream];

    // Initialize the streams and generate the names
    for (i = 0; i < num_stream; i++)
    {
        streamNameIN[i] = baseName + std::to_string(i);
        streamNameOUT[i] = baseName + '_' + std::to_string(i);
        RedisCommand(c, "XTRIM %s MINID 0", streamNameOUT[i].c_str());
        initStreams(c,streamNameIN[i].c_str(),"mean");
    }

    for (i = 0; i < num_stream; i++)
    {
        threads[i] = std::thread(ReadMessage, streamNameIN[i], id, std::ref(windows[i]),std::ref(toSend[i]));
    }

    while(1)
    {
        if(done.load() >= num_stream)
        { 
            for (i = 0; i < num_stream; i++)
            {   
                std::cout << streamNameOUT[i] << ":" << toSend[i] << std::endl;
                SendMessage(c,toSend[i],streamNameOUT[i]);

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
