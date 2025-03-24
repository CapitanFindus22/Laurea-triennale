#include "main.hpp"

// Calculate the mean
double Mean(std::deque<double> dq)
{

    double sum = 0;

    for (double num : dq)
    {
        sum += num;
    }

    return sum / dq.size();
}