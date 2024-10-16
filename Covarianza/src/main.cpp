#include "main.hpp"

double difference;
bool firstRound = true;

int main()
{

#if (DEBUG > 0)
    setvbuf(stdout, (char *)NULL, _IONBF, 0);
    setvbuf(stderr, (char *)NULL, _IONBF, 0);
#endif

    // Connection to DB
    Con2DB db(IP, PORT_DB, USERNAME, PASSWORD, DB_NAME);

    // Connection to Redis
    redisContext *c = redisConnect(IP, PORT);

    // Counter
    size_t i;

    initStreams(c, ISTREAM, GROUPNAME);
    initStreams(c, "M3", GROUPNAME);
    initStreams(c, "M4", GROUPNAME);

    // Get info
    size_t num_stream = std::stoi(ReadMessage(c, ISTREAM, GROUPNAME, NAME));
    int id = std::stoi(ReadMessage(c, ISTREAM, GROUPNAME, NAME));
    ReadMessage(c, ISTREAM, GROUPNAME, NAME);

    std::cout << "Sessione n°" << id << " Numero di stream: " << num_stream << std::endl;

    // Set the threshold
    difference = SetDiff();

    // Names used for the streams
    std::string names[num_stream];
    std::string baseName = "STREAM_";

    // Will contain the strings from the stream
    std::string windows[num_stream];

    // Will contain the values
    std::vector<std::vector<double>> values(num_stream);

    // Initialize the streams and generate the names
    for (i = 0; i < num_stream; i++)
    {
        names[i] = baseName + std::to_string(i);
        initStreams(c, names[i].c_str(), GROUPNAME);
    }

    // To stop the loop
    size_t RowsToCalc = std::stoi(ReadMessage(c, ISTREAM, GROUPNAME, NAME));
    size_t RowsCalculated = 0;

    // Main loop
    while (RowsCalculated < RowsToCalc)
    {
        // Get the strings
        for (i = 0; i < num_stream; i++)
        {
            windows[i] = ReadMessage(c, names[i], GROUPNAME, NAME);
        }

        // Transform the strings to doubles
        for (i = 0; i < num_stream; i++)
        {
            String2Double(windows[i], std::ref(values[i]));
        }

        // Calculate covariance
        Covariance(c, std::ref(db), std::ref(values), id);

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
