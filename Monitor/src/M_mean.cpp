#include "main.hpp"

// Monitor for the Mean calculation
void MM()
{
    Con2DB db(IP, PORT_DB, USERNAME, PASSWORD, DB_NAME);
    redisContext *c = redisConnect(IP, PORT);

    std::string StreamName;
    double mean;
    int id;

    redisCommand(c, "XTRIM %s MAXLEN 0", MONITOR_M_STREAM);
    redisCommand(c, "XTRIM M1 MAXLEN 0");

    // Get info
    initStreams(c, ISTREAM, MONITOR_M_GROUP);
    ReadMessage(c, ISTREAM, MONITOR_M_GROUP, NAME, true);
    id = std::stoi(ReadMessage(c, ISTREAM, MONITOR_M_GROUP, NAME, true));

    initStreams(c, MONITOR_M_STREAM, MONITOR_M_GROUP);

    while (1)
    {
        // Get value and StreamName to check
        mean = std::stod(ReadMessage(c, MONITOR_M_STREAM, MONITOR_M_GROUP, NAME, true));
        StreamName = ReadMessage(c, MONITOR_M_STREAM, MONITOR_M_GROUP, NAME, true);

        // Check on the db and write result on the db
        log2db(std::ref(db), (logfromdb(std::ref(db), StreamName, "", true, id) == mean) ? true : false, true, StreamName, "", id);

        // Send ACK
        SendMessage(c, "1", "M1");
    }

    return;
}