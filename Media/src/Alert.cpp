#include "main.hpp"

extern float difference;

void Alert(Con2DB& db, std::string streamName, float mean, int id)
{

    float old_val = logfromdb(std::ref(db), streamName);

    if ((mean > old_val + difference) || (mean < old_val - difference))
    {

        logAlert(std::ref(db), mean - old_val, streamName, id);
    }
}