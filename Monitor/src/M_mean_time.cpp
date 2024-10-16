#include "main.hpp"

// Monitor that calculate the time passed before sending an alert (mean)
void MMT()
{
    Con2DB db(IP, PORT_DB, USERNAME, PASSWORD, DB_NAME);
    redisContext *c = redisConnect(IP, PORT);

    std::string StreamName;
    int id;

    redisCommand(c, "XTRIM %s MAXLEN 0", MONITOR_MT_STREAM);
    redisCommand(c, "XTRIM M2 MAXLEN 0");

    // Get info
    initStreams(c, ISTREAM, MONITOR_MT_GROUP);
    ReadMessage(c, ISTREAM, MONITOR_MT_GROUP, NAME);
    id = std::stoi(ReadMessage(c, ISTREAM, MONITOR_MT_GROUP, NAME));

    initStreams(c, MONITOR_MT_STREAM, MONITOR_MT_GROUP);

    while (1)
    {
        // Get StreamName to check
        StreamName = ReadMessage(c, MONITOR_MT_STREAM, MONITOR_MT_GROUP, NAME);

        // Check on the db and write result on the db
        log2db_time(std::ref(db), true, StreamName, "", id, logfromdb_time(std::ref(db), StreamName, "", true, id));

        // Send ACK
        SendMessage(c, "1", "M2");
    }

    return;
}