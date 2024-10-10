#include "main.hpp"

extern size_t windowLength;

void Covariance(Con2DB db, std::deque<float> arr[], size_t size, int id){

    size_t i,j,k;
    float v1,v2;
    double covariance;

    for (auto &v : arr[2])
    {
        std::cout << v << std::endl;
    }

    for (i = 0; i < size-1; i++)
    {
        for (j = i+1; j < size; j++)
        {

            v1 = Mean(std::ref(arr[i]));
            v2 = Mean(std::ref(arr[j]));            

            covariance = 0;

            for (k = 0; k < windowLength; k++)
            {
                covariance += (arr[i][k] - v1)*(arr[j][k] - v2);
            }
            
            covariance /= (windowLength-1);
            log2db(db,covariance, "STREAM" + std::to_string(i), "STREAM" + std::to_string(j), id);

        }
        
    }
    

}