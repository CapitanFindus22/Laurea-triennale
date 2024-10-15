#include "main.hpp"

size_t ChooseSize()
{

    size_t result;

    while (1)
    {
        std::cout << "Grandezza della finestra: ";
        std::cin >> result;

        if (std::cin.fail()) {
        std::cout << "Input non valido. Per favore, inserisci un numero intero." << std::endl;
        std::cin.clear(); // Pulisce il flag di errore
        std::cin.ignore(1000000, '\n');

        }

        else if (result <= 0)
        {
            std::cout << "Scegliere un numero intero" << std::endl;
        }

        else {break;}
        
    }

    return result;
}