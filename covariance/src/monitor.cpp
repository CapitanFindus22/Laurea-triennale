#include "main.hpp"

extern float difference;

void monitor(Con2DB db, std::string streamName1, std::string streamName2, float mean, int id)
{

    float old_val = logfromdb(db, streamName1, streamName2);

    if ((mean > old_val + difference) || (mean < old_val - difference))
    {

        logMonitor(db, mean - old_val, streamName1, streamName2, id);
    }
}