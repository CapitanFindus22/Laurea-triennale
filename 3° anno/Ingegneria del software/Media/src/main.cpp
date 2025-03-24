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

    // Create groups
    initStreams(c, ISTREAM, GROUPNAME);
    initStreams(c, "M1", GROUPNAME);
    initStreams(c, "M2", GROUPNAME);
    initStreams(c, "M5", GROUPNAME);

    // Connection to DB
    Con2DB db(IP, PORT_DB, USERNAME, PASSWORD, DB_NAME);
    size_t i;

    // Get info
    size_t num_stream = std::stoi(ReadMessage(c, ISTREAM, GROUPNAME, NAME));
    int id = std::stoi(ReadMessage(c, ISTREAM, GROUPNAME, NAME));
    size_t RowstoRead = std::stoi(ReadMessage(c, ISTREAM, GROUPNAME, NAME));

    std::cout << "Sessione n°" << id << " Numero di stream: " << num_stream << std::endl;
    std::cout << "La simulazione leggerà " << RowstoRead << " righe" << std::endl;

    // Set the size of the window
    size_t windowLength = ChooseSize(RowstoRead);
    std::cout << "La finestra contiene " << windowLength << " elementi" << std::endl;

    // To stop the loop
    size_t RowsToCalc = RowstoRead - windowLength + 1;
    size_t RowsCalculated = 0;

    // Names used for the streams
    std::string baseNameIN = "STREAM";
    std::string baseNameOUT = "STREAM_";
    std::string StreamNameIN[num_stream];
    std::string StreamNameOUT[num_stream];

    // Initialize the streams and generate the names
    for (i = 0; i < num_stream; i++)
    {
        StreamNameIN[i] = baseNameIN + std::to_string(i);
        StreamNameOUT[i] = baseNameOUT + std::to_string(i);
        initStreams(c, StreamNameIN[i].c_str(), GROUPNAME);
    }

    // Will contain the values
    std::deque<double> windows[num_stream];

    // Will contain the windows
    std::string toSend[num_stream];

    // Set the threshold
    difference = SetDiff();

    float mean;
    bool firstRound = true;

    // Send info to Covariance
    SendMessage(c, std::to_string(RowsToCalc), ISTREAM);

    // Fill the window
    while (windows[num_stream - 1].size() < windowLength)
    {
        for (i = 0; i < num_stream; i++)
        {
            windows[i].push_back(std::stod(ReadMessage(c, StreamNameIN[i], GROUPNAME, NAME)));
        }
    }

    // Main loop
    while (RowsCalculated < RowsToCalc)
    {

        if (windows[0].size() < windowLength)
        {
            // Get new value
            for (i = 0; i < num_stream; i++)
            {
                windows[i].push_back(std::stod(ReadMessage(c, StreamNameIN[i], GROUPNAME, NAME)));
            }
        }

        for (i = 0; i < num_stream; i++)
        {
            // Calculate mean
            mean = Mean(windows[i]);

            std::cout << "La media di " << StreamNameIN[i] << " è " << mean << std::endl;

            log2db(std::ref(db), std::to_string(mean), StreamNameIN[i], id);

            // Check
            SendMessage(c, StreamNameIN[i], "MMonitor");
            SendMessage(c, std::to_string(mean), "MMonitor");
            ReadMessage(c, "M1", GROUPNAME, NAME);

            // Send alert
            if (!firstRound)
            {
                Alert(c, std::ref(db), StreamNameIN[i], mean, id);
            }

            // Convert and send the windows
            toSend[i] = d2s(windows[i]) + std::to_string(mean);

            std::cout << "Invio: " << toSend[i] << " a " << StreamNameOUT[i] << "\n"
                      << std::endl;

            // Send the window to Covariance
            SendMessage(c, toSend[i], StreamNameOUT[i]);

            // Remove oldest value
            windows[i].pop_front();
        }

        // Start alert
        firstRound = false;

        RowsCalculated++;
    }

    std::cout << "Fine simulazione" << std::endl;

    // Close the connection
    redisFree(c);
    db.finish();

    return 0;
}
