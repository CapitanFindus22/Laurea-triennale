#include "main.hpp"

extern bool firstRound;

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
    initStreams(c, "M3", "covariance");
    initStreams(c, "M4", "covariance");

    size_t num_stream = std::stoi(ReadInfo(c,"INFOSTREAM"));
    
    int id = std::stoi(ReadInfo(c,"INFOSTREAM"));

    std::cout << "Sessione n°" << id << " Numero di stream: " << num_stream << std::endl;

    // Stuff for the threads
    std::string names[num_stream];
    std::string windows[num_stream];
    std::vector<std::vector<double>> values(num_stream);

    // Initialize the streams and generate the names
    for (i = 0; i < num_stream; i++)
    {
        names[i] = baseName + std::to_string(i);
        initStreams(c,names[i].c_str(),"covariance");
    }

    while(1)
    {

        for (i = 0; i < num_stream; i++)
        {
            windows[i] = ReadMessage(c,names[i]);
        }

        for (i = 0; i < num_stream; i++)
        {
            String2Float(windows[i],std::ref(values[i]));
        }
        
        Covariance(c,std::ref(db1),std::ref(values),id);
        
        firstRound = false;
    }

    // Close the connection
    redisFree(c);

    return 0;
}
