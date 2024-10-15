#include "main.hpp"

extern double difference;

void Alert(redisContext *c,Con2DB& db, std::string streamName1, std::string streamName2, double covariance, int id)
{

    double old_val = logfromdb(db, streamName1, streamName2);

    if ((covariance > old_val + difference) || (covariance < old_val - difference))
    {
        logAlert(db, covariance - old_val, streamName1, streamName2, id);
        SendMessage(c, streamName1,"CTMonitor");
        SendMessage(c, streamName2,"CTMonitor");
        ReadMessage(c,"M4");   
    }
}