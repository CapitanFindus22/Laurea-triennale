#include "main.hpp"

// Set the size of the window
size_t ChooseSize(size_t maxSize)
{

    size_t result;

    while (1)
    {
        std::cout << "Grandezza della finestra: ";
        std::cin >> result;

        if (std::cin.fail())
        {
            std::cout << "Inserire un numero intero" << std::endl;
            std::cin.clear(); // Pulisce il flag di errore
            std::cin.ignore(1000000, '\n');
        }

        else if (result <= 0)
        {
            std::cout << "Ci deve essere almeno un elemento" << std::endl;
            std::cin.clear();
            std::cin.ignore(1000000, '\n');
        }

        else if (result > maxSize)
        {
            std::cout << "La finestra può contenere massimo " << maxSize << " elementi" << std::endl;
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