#include "main.hpp"

extern double difference;

// Send an alert if appropriate
void Alert(redisContext *c, Con2DB &db, std::string StreamName1, std::string StreamName2, double covariance, int id)
{
    // Get last value
    double old_val = logfromdb(db, StreamName1, StreamName2, id);

    // Compare
    if ((covariance > old_val + difference) || (covariance < old_val - difference))
    {
        // Time monitor
        logAlert(db, covariance - old_val, StreamName1, StreamName2, id);
        SendMessage(c, StreamName1, "CTMonitor");
        SendMessage(c, StreamName2, "CTMonitor");
        ReadMessage(c, "M4", GROUPNAME, NAME);

        std::cout << "Attenzione!! Il nuovo valore della covarianza tra " << StreamName1 << " e " << StreamName2 << " differisce di " << covariance - old_val << " dal precedente" << std::endl;

    }
}