#include "main.hpp"

// Monitor that calculate the time passed before sending an alert (covariance)
void MCT()
{
    Con2DB db(IP, PORT_DB, USERNAME, PASSWORD, DB_NAME);
    redisContext *c = redisConnect(IP, PORT);

    std::string StreamName1, StreamName2;
    int id;

    redisCommand(c, "XTRIM %s MAXLEN 0", MONITOR_CT_STREAM);
    redisCommand(c, "XTRIM M4 MAXLEN 0");

    // Get info
    initStreams(c, ISTREAM, MONITOR_CT_GROUP);
    ReadMessage(c, ISTREAM, MONITOR_CT_GROUP, NAME);
    id = std::stoi(ReadMessage(c, ISTREAM, MONITOR_CT_GROUP, NAME));

    initStreams(c, MONITOR_CT_STREAM, MONITOR_CT_GROUP);

    while (1)
    {
        // Get the StreamNames to check
        StreamName1 = ReadMessage(c, MONITOR_CT_STREAM, MONITOR_CT_GROUP, NAME);
        StreamName2 = ReadMessage(c, MONITOR_CT_STREAM, MONITOR_CT_GROUP, NAME);

        // Check on the db and write result on the db
        log2db_time(std::ref(db), false, StreamName1, StreamName2, id, logfromdb_time(std::ref(db), StreamName1, StreamName2, false, id));

        // Send ACK
        SendMessage(c, "1", "M4");
    }

    return;
}