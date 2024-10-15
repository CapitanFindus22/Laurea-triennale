#include "main.hpp"

double Mean(std::deque<double> dq)
{

    double sum = 0;

    for (double num : dq)
    {
        sum += num;
    }

    return sum / dq.size();
}