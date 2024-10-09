#include "main.hpp"

float Mean(std::deque<float> dq)
{

    float sum = 0;

    for (float num : dq)
    {
        sum += num;
    }

    return sum / dq.size();
}