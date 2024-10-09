#include "main.hpp"

extern float difference;

void monitor(Con2DB db, std::string streamName, float mean, int id)
{

    float old_val = logfromdb(db, streamName);

    if ((mean > old_val + difference) || (mean < old_val - difference))
    {

        logMonitor(db, mean - old_val, streamName, id);
    }
}