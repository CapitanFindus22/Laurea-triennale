#include "main.hpp"

double difference;

int main()
{

#if (DEBUG > 0)
    setvbuf(stdout, (char *)NULL, _IONBF, 0);
    setvbuf(stderr, (char *)NULL, _IONBF, 0);
#endif

    // Connection to Redis
    redisContext *c = redisConnect(IP, PORT);

    //Create groups
    initStreams(c, "INFOSTREAM", "mean");
    initStreams(c, "M1", "mean");
    initStreams(c, "M2", "mean");
    initStreams(c, "M5", "mean");

    // Connection to DB
    Con2DB db(IP, PORT_DB, USERNAME, PASSWORD, DB_NAME);
    size_t i;

    //Get info
    size_t num_stream = std::stoi(ReadMessage(c,"INFOSTREAM"));
    int id = std::stoi(ReadMessage(c,"INFOSTREAM"));
    size_t RowstoRead = std::stoi(ReadMessage(c,"INFOSTREAM"));

    std::cout << "Sessione n°" << id << " Numero di stream: " << num_stream << std::endl;
    std::cout << "La simulazione leggerà " << RowstoRead << " righe" << std::endl;

    //Set the size of the window
    size_t windowLength = ChooseSize(RowstoRead);
    std::cout << "La finestra contiene " << windowLength << " elementi" << std::endl;

    size_t RowsToCalc = RowstoRead-windowLength+1;
    size_t RowsCalculated = 0;

    //Names used for the streams
    std::string baseName = "STREAM";
    std::string streamNameIN[num_stream];
    std::string streamNameOUT[num_stream];

    //Will contain the values
    std::deque<double> windows[num_stream];

    //Will contain the window to send to the Covariance calculator
    std::string toSend[num_stream];

    float mean;
    bool isEmpty = true;

    difference = SetDiff();

    // Initialize the streams and generate the names
    for (i = 0; i < num_stream; i++)
    {
        streamNameIN[i] = baseName + std::to_string(i);
        streamNameOUT[i] = baseName + '_' + std::to_string(i);
        RedisCommand(c, "XTRIM %s MAXLEN 0", streamNameOUT[i].c_str());
        initStreams(c, streamNameIN[i].c_str(), "mean");
    }

    //Fill the window
    while (windows[0].size() < windowLength)
    {
        for (i = 0; i < num_stream; i++)
        {
            windows[i].push_back(std::stod(ReadMessage(c, streamNameIN[i])));
        }
    }

    while (RowsCalculated<RowsToCalc)
    {

        for (i = 0; i < num_stream; i++)
        {
            mean = Mean(windows[i]);

            log2db(std::ref(db), std::to_string(mean), streamNameIN[i], id);

            //Check
            SendMessage(c, std::to_string(mean), "MMonitor");
            SendMessage(c, streamNameIN[i], "MMonitor");
            ReadMessage(c, "M1");

            //Send alert
            if (!isEmpty)
            {
                Alert(c, std::ref(db), streamNameIN[i], mean, id);
            }

            //Convert and send the windows
            toSend[i] = d2s(windows[i]);
            SendMessage(c, toSend[i], streamNameOUT[i]);

            //Remove oldest value
            windows[i].pop_front();
        }

        isEmpty = false;

        //Get new value
        for (i = 0; i < num_stream; i++)
        {
            windows[i].push_back(std::stod(ReadMessage(c, streamNameIN[i])));
        }

        RowsCalculated++;
        std::cout << RowsCalculated << std::endl;
    }

    // Close the connection
    redisFree(c);
    db.finish();

    return 0;
}
