#include "main.hpp"

// Extract the values from the string and put them in the array
void String2FloatArray(std::string str, std::vector<float> val)
{

    std::string temp;
    size_t i = 0;

    for (char &c : str)
    {

        if (c == ',' && !temp.empty())
        {

            val[i] = std::stof(temp);
            temp.clear();
            i++;
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