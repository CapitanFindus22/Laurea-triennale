#include "main.hpp"

// Generate a string from the deque values
std::string d2s(std::deque<double> dq)
{
    std::string result;

    for (size_t i = 0; i < dq.size(); ++i)
    {
        result += std::to_string(dq[i]);
        if (i < dq.size() - 1)
        {
            result += ",";
        }
    }

    return result;
}