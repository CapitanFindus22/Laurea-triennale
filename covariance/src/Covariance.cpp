#include "main.hpp"

extern size_t windowLength;

void Covariance(std::deque<float> arr[]){

    size_t size = sizeof(arr)/sizeof(arr[0]);
    size_t i,j,k;
    float v1,v2;
    float covariance = 0;

    for (i = 0; i < size-1; i++)
    {
        for (j = 0; j < size; j++)
        {
            v1 = Mean(arr[i]);
            v1 = Mean(arr[j]);

            for (k = 0; i < windowLength; k++)
            {
                covariance += (arr[i][k] - v1)*(arr[i][k] - v2);

            }
            
            covariance /= (windowLength-1);

            //Salvare su DB

        }
        
    }
    

}