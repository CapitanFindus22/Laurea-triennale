#include "main.hpp"

std::pair<float,std::string> Split(std::string str)
{
    std::string t1,t2;
    bool div = false;

    for (auto &ch : str)
    {
        if (ch == ',')
        {
            div = true;
        }

        else if(!div){t1 += ch;}
        else {t2 += ch;}
        
    }
    
    return {std::stof(t1),t2};

}