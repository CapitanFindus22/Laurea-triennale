#include "main.hpp"

extern double difference;

// Send the alert if appropriate
void Alert(redisContext *c, Con2DB &db, std::string StreamName, double mean, int id)
{
    // Get last value
    double old_val = logfromdb(std::ref(db), StreamName);

    // Compare
    if ((mean > old_val + difference) || (mean < old_val - difference))
    {
        logAlert(std::ref(db), std::to_string(mean - old_val), StreamName, id);

        // Control monitor
        SendMessage(c, StreamName, "AMonitor");
        SendMessage(c, std::to_string(mean - old_val), "AMonitor");
        ReadMessage(c, "M5", GROUPNAME, NAME);

        // Time monitor
        SendMessage(c, StreamName, "TMonitor");
        ReadMessage(c, "M2", GROUPNAME, NAME);

    }
}