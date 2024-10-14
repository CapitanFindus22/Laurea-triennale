#include "main.hpp"

// Extract the values from the string and put them in the array
void String2FloatArray(std::string str, char delimiter, float val[])
{

    std::string temp;
    size_t i = 0;

    for (char &c : str)
    {

        if (c == delimiter && !temp.empty())
        {

            val[i] = std::stof(temp);
            i++;
            temp.clear();
        }

        else
        {

            temp += c;
        }
    }

    if (!temp.empty())
    {

        val[i] = std::stof(temp);
        temp.clear();
    }
}