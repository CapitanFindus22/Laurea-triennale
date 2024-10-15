#include "main.hpp"

std::string d2s(std::deque<double> dq)
{
    std::string result;

    for (size_t i = 0; i < dq.size(); ++i) {
        result += std::to_string(dq[i]);
        if (i < dq.size() - 1) {
            result += ",";  // Aggiungi una virgola dopo ogni elemento tranne l'ultimo
        }
    }

    return result;
}