#include "main.hpp"

// Monitor for the Covariance calculation
void MC()
{
    Con2DB db(IP, PORT_DB, USERNAME, PASSWORD, DB_NAME);
    redisContext *c = redisConnect(IP, PORT);

    std::string StreamName1, StreamName2;
    double covariance;
    int id;

    // Get info
    initStreams(c, ISTREAM, MONITOR_C_GROUP);

    ReadMessage(c, ISTREAM, MONITOR_C_GROUP, NAME);
    id = std::stoi(ReadMessage(c, ISTREAM, MONITOR_C_GROUP, NAME));
    ReadMessage(c, ISTREAM, MONITOR_C_GROUP, NAME);
    ReadMessage(c, ISTREAM, MONITOR_C_GROUP, NAME);

    initStreams(c, MONITOR_C_STREAM, MONITOR_C_GROUP);

    while (1)
    {
        // Get value and the two StreamNames to check
        StreamName1 = ReadMessage(c, MONITOR_C_STREAM, MONITOR_C_GROUP, NAME);

        if (StreamName1 == "end")
        {
            break;
        }

        StreamName2 = ReadMessage(c, MONITOR_C_STREAM, MONITOR_C_GROUP, NAME);
        covariance = std::stod(ReadMessage(c, MONITOR_C_STREAM, MONITOR_C_GROUP, NAME));

        // Check on the db and write result on the db
        log2db(std::ref(db), (logfromdb(std::ref(db), StreamName1, StreamName2, false, id) == covariance) ? true : false, false, StreamName1, StreamName2, id);

        // Send ACK
        SendMessage(c, "1", "M3");
    }

    return;
}