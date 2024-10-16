#include "main.hpp"

extern double difference;

void Alert(redisContext *c, Con2DB &db, std::string streamName, double mean, int id)
{

    double old_val = logfromdb(std::ref(db), streamName);

    if ((mean > old_val + difference) || (mean < old_val - difference))
    {
        logAlert(std::ref(db), std::to_string(mean - old_val), streamName, id);

        SendMessage(c, streamName, "TMonitor");
        ReadMessage(c, "M2");

        SendMessage(c, streamName, "AMonitor");
        SendMessage(c, std::to_string(mean - old_val), "AMonitor");
        ReadMessage(c, "M5");
    }
}