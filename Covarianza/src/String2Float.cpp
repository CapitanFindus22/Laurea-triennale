#include "main.hpp"

// Extract the values from the string and put them in the array
void String2Float(std::string str, std::vector<double>& val)
{

    std::string temp;
    size_t i = 0;
    val.clear();

    for (char &c : str)
    {

        if (c == ',' && !temp.empty())
        {

            val.push_back(std::stof(temp));
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

        val.push_back(std::stof(temp));
        temp.clear();
    }

}