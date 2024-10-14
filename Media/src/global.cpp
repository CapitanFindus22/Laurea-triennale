#include "main.hpp"

std::atomic<bool> running{false};
std::atomic<size_t> done{0};
size_t windowLength = 5;
float difference = 0.9;