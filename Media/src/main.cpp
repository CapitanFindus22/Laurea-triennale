#include "main.hpp"

extern bool isEmpty;
extern size_t windowLength;

int main()
{

#if (DEBUG > 0)
    setvbuf(stdout, (char *)NULL, _IONBF, 0);
    setvbuf(stderr, (char *)NULL, _IONBF, 0);
#endif

    // Connection to Redis
    redisContext *c = redisConnect(IP, PORT);

    Con2DB db(IP, PORT_DB, USERNAME, PASSWORD, DB_NAME);

    std::string baseName = "STREAM";
    size_t i;

    initStreams(c, "INFOSTREAM", "mean");
    initStreams(c, "M1", "mean");
    initStreams(c, "M2", "mean");
    initStreams(c, "M5", "mean");

    size_t num_stream = std::stoi(ReadInfo(c));
    int id = std::stoi(ReadInfo(c));

    std::cout << "Sessione n°" << id << " Numero di stream: " << num_stream << std::endl;

    // Stuff for the threads
    std::string streamNameIN[num_stream];
    std::string streamNameOUT[num_stream];
    std::deque<double> windows[num_stream];
    std::string toSend[num_stream];
    float mean;

    // Initialize the streams and generate the names
    for (i = 0; i < num_stream; i++)
    {
        streamNameIN[i] = baseName + std::to_string(i);
        streamNameOUT[i] = baseName + '_' + std::to_string(i);
        RedisCommand(c, "XTRIM %s MAXLEN 0", streamNameOUT[i].c_str());
        initStreams(c, streamNameIN[i].c_str(), "mean");
    }

    windowLength = ChooseSize();
    std::cout << "La finestra contiene " << windowLength << " elementi" << std::endl;

    while (windows[0].size() < windowLength)
    {
        for (i = 0; i < num_stream; i++)
        {
            windows[i].push_back(ReadMessage(c, streamNameIN[i]));
        }
    }

    int j = 0;

    while (1)
    {

        for (i = 0; i < num_stream; i++)
        {
            mean = Mean(windows[i]);

            log2db(std::ref(db), std::to_string(mean), streamNameIN[i], id);

            SendMessage(c, std::to_string(mean), "MMonitor");
            SendMessage(c, streamNameIN[i], "MMonitor");
            ReadMessage(c, "M1");

            if (!isEmpty)
            {
                Alert(c, std::ref(db), streamNameIN[i], mean, id);
            }

            toSend[i] = d2s(windows[i]);
            SendMessage(c, toSend[i], streamNameOUT[i]);
            std::cout << toSend[i] << std::endl;
            windows[i].pop_front();
        }

        isEmpty = false;

        for (i = 0; i < num_stream; i++)
        {
            windows[i].push_back(ReadMessage(c, streamNameIN[i]));
        }

        printf("\n%d\n", j);

        j++;
    }

    // Close the connection
    redisFree(c);

    return 0;
}
