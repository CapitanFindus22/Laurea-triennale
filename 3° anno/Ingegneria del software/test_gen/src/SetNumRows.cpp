#include "main.hpp"

// Set the total number of rows to send
size_t SetNum()
{

    size_t result;

    while (1)
    {
        std::cout << "Scegliere quante righe inviare: ";
        std::cin >> result;

        if (std::cin.fail())
        {
            std::cout << "Inserire un numero" << std::endl;
            std::cin.clear();
            std::cin.ignore(1000000, '\n');
        }

        else if (result < 2)
        {
            std::cout << "Ci devono essere almeno 2 elementi" << std::endl;
            std::cin.clear();
            std::cin.ignore(1000000, '\n');
        }

        else
        {
            break;
        }
    }

    return result;
}