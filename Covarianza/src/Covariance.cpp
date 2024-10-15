#include "main.hpp"

extern size_t windowLength;
extern bool firstRound;

void Covariance(Con2DB& db, std::vector<std::vector<float>>& arr, int id){

    size_t i,j,k;
    float v1,v2;
    float covariance;            

    for (i = 0; i < arr.size()-1; i++) 
    {
        for (j = i+1; j < arr.size(); j++)
        {        

            covariance = 0;

            for (k = 0; k < windowLength; k++)
            {
                v1 = arr[i][k] - arr[i].back();
                v2 = arr[j][k] - arr[j].back();
                covariance += (v1*v2);

            }

            covariance /= (windowLength);

            std::cout << covariance << std::endl;

            if(!firstRound) {Alert(db, "STREAM" + std::to_string(i), "STREAM" + std::to_string(j), covariance,id);}
            log2db(db,covariance, "STREAM" + std::to_string(i), "STREAM" + std::to_string(j), id);
            
        }
        
    }
    
    firstRound = false;

}