#include "main.hpp"

std::atomic<bool> running{true};
std::atomic<size_t> done{0};
size_t windowLength = 4;
float difference = 2;