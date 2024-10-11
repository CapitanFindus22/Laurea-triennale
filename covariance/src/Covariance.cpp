#include "main.hpp"

extern size_t windowLength;

void Covariance(Con2DB db, std::vector<std::vector<float>> arr, size_t size, int id){

    size_t i,j,k;
    float v1,v2;
    double covariance;

    for (i = 0; i < size-1; i++) 
    {
        for (j = i+1; j < size; j++)
        {        

            covariance = 0;

            for (k = 0; k < windowLength; k++)
            {
                covariance += (arr[i][k] - arr[i][-1])*(arr[j][k] - arr[j][-1]);
            }
            
            covariance /= (windowLength-1);

            log2db(db,covariance, "STREAM" + std::to_string(i), "STREAM" + std::to_string(j), id);
            monitor(db, "STREAM" + std::to_string(i), "STREAM" + std::to_string(j), covariance,id);

        }
        
    }
    

}