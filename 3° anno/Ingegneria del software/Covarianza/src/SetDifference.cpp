#include "main.hpp"

// Set the difference for the alert
double SetDiff()
{

    double result;

    while (1)
    {
        std::cout << "Scegliere il valore di threshold per gli alert: ";
        std::cin >> result;

        if (std::cin.fail())
        {
            std::cout << "Inserire un numero" << std::endl;
            std::cin.clear();
            std::cin.ignore(1000000, '\n');
        }

        else if (result == 0)
        {
            std::cout << "Non può essere zero" << std::endl;
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