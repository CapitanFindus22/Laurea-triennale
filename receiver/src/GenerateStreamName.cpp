#include "main2.hpp"

// Combine base and number to create a name
// Example: STREAM_ + 5 -> STREAM_5
std::string GenerateStreamName(std::string base, size_t number)
{

    return base + std::to_string(number);
}