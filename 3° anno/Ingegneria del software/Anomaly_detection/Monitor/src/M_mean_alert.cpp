#include "main.hpp"

// Check if the alert sent has the right value
void MMA()
{
    Con2DB db(IP, PORT_DB, USERNAME, PASSWORD, DB_NAME);
    redisContext *c = redisConnect(IP, PORT);

    std::string StreamName;
    double diff;
    int id;

    // Get info
    initStreams(c, ISTREAM, MONITOR_MA_GROUP);

    ReadMessage(c, ISTREAM, MONITOR_MA_GROUP, NAME);
    id = std::stoi(ReadMessage(c, ISTREAM, MONITOR_MA_GROUP, NAME));
    ReadMessage(c, ISTREAM, MONITOR_MA_GROUP, NAME);
    ReadMessage(c, ISTREAM, MONITOR_MA_GROUP, NAME);

    initStreams(c, MONITOR_MA_STREAM, MONITOR_MA_GROUP);

    while (1)
    {
        // Get value and the StreamName to check
        StreamName = ReadMessage(c, MONITOR_MA_STREAM, MONITOR_MA_GROUP, NAME);

        if (StreamName == "end")
        {
            break;
        }

        diff = std::stod(ReadMessage(c, MONITOR_MA_STREAM, MONITOR_MA_GROUP, NAME));

        std::cout << "Attenzione!! Il nuovo valore della media di " << StreamName << " differisce di " << diff << " dal precedente" << std::endl;

        // Check on the db and write result on the db
        log2db_alert(std::ref(db), (logfromdb_alert(std::ref(db), StreamName, id) == diff) ? true : false, StreamName, id);

        // Send ACK
        SendMessage(c, "1", "M5");
    }

    return;
}