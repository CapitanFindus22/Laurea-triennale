#include "main.hpp"

extern float difference;

void Alert(Con2DB db, std::string streamName1, std::string streamName2, float covariance, int id)
{

    float old_val = logfromdb(db, streamName1, streamName2);

    if ((covariance > old_val + difference) || (covariance < old_val - difference))
    {

        logAlert(db, covariance - old_val, streamName1, streamName2, id);
    }
}