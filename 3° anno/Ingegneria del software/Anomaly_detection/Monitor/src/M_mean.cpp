#include "main.hpp"

// Monitor for the Mean calculation
void MM()
{
    Con2DB db(IP, PORT_DB, USERNAME, PASSWORD, DB_NAME);
    redisContext *c = redisConnect(IP, PORT);

    std::string StreamName;
    double mean;
    int id;

    // Get info
    initStreams(c, ISTREAM, MONITOR_M_GROUP);

    ReadMessage(c, ISTREAM, MONITOR_M_GROUP, NAME);
    id = std::stoi(ReadMessage(c, ISTREAM, MONITOR_M_GROUP, NAME));
    ReadMessage(c, ISTREAM, MONITOR_M_GROUP, NAME);
    ReadMessage(c, ISTREAM, MONITOR_M_GROUP, NAME);

    initStreams(c, MONITOR_M_STREAM, MONITOR_M_GROUP);

    while (1)
    {

        // Get value and StreamName to check
        StreamName = ReadMessage(c, MONITOR_M_STREAM, MONITOR_M_GROUP, NAME);

        if (StreamName == "end")
        {
            break;
        }

        mean = std::stod(ReadMessage(c, MONITOR_M_STREAM, MONITOR_M_GROUP, NAME));

        // Check on the db and write result on the db
        log2db(std::ref(db), (logfromdb(std::ref(db), StreamName, "", true, id) == mean) ? true : false, true, StreamName, "", id);

        // Send ACK
        SendMessage(c, "1", "M1");
    }

    return;
}