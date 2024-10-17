#include "main.hpp"

extern bool firstRound;

// Calculate the covariance and save it on the DB
void Covariance(redisContext *c, Con2DB &db, std::vector<std::vector<double>> &arr, int id)
{

    size_t i, j, k;
    double v1, v2;
    double covariance;

    for (i = 0; i < arr.size() - 1; i++)
    {
        for (j = i + 1; j < arr.size(); j++)
        {

            covariance = 0;

            for (k = 0; k < arr[0].size() - 1; k++)
            {
                v1 = arr[i][k] - arr[i].back();
                v2 = arr[j][k] - arr[j].back();
                covariance += (v1 * v2);
            }

            covariance /= (arr[0].size() - 2);

            log2db(db, covariance, "STREAM" + std::to_string(i), "STREAM" + std::to_string(j), id);

            // Control monitor
            SendMessage(c, std::to_string(covariance), "CMonitor");
            SendMessage(c, "STREAM" + std::to_string(i), "CMonitor");
            SendMessage(c, "STREAM" + std::to_string(j), "CMonitor");
            ReadMessage(c, "M3", GROUPNAME, NAME, true);

            // Send alert
            if (!firstRound)
            {
                Alert(c, db, "STREAM" + std::to_string(i), "STREAM" + std::to_string(j), covariance, id);
            }
        }
    }
}