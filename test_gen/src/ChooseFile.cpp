#include "main.hpp"

std::string ChooseFile()
{

    std::filesystem::path path = CSV_PATH;
    size_t result;
    size_t numFiles = 0;
    std::unordered_map<int,std::string> hashMap;

    std::cout << "Scegliere uno dei seguenti file: " << std::endl;

    for (const auto &entry : std::filesystem::directory_iterator(path))
    {
        if (entry.is_regular_file() && entry.path().extension() == ".csv")
        {
            numFiles++;
            std::cout << numFiles << ") " << entry.path().filename() << std::endl;
            hashMap[numFiles] = entry.path().filename();
        }
    }

    while (1)
    {
        std::cout << "Numero del file: ";
        std::cin >> result;

        if (std::cin.fail()) {
        std::cout << "Input non valido. Per favore, inserisci un numero intero." << std::endl;
        std::cin.clear(); // Pulisce il flag di errore
        std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');

        }

        else if (result <= 0 || result > numFiles)
        {
            std::cout << "\nIl file con numero " << result << " non esiste nella cartella, sceglierne un altro." << std::endl;
        }

        else {break;}
        
    }

    return hashMap[result];
}