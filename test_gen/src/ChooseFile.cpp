#include "main.hpp"

std::string ChooseFile()
{

    std::filesystem::path path = CSV_PATH;
    std::string result;

    std::cout << "Scegliere uno dei seguenti file: " << std::endl;

    for (const auto &entry : std::filesystem::directory_iterator(path))
    {
        if (entry.is_regular_file() && entry.path().extension() == ".csv")
        {
            std::cout << entry.path().filename() << std::endl;
        }
    }

    std::cout << "Nome del file scelto (senza \"\"): ";

    std::cin >> result;

    while (!std::filesystem::exists(path / result))
    {
        std::cout << "\nIl file " << result << " non esiste nella cartella, sceglierne un altro." << std::endl;
        std::cout << "Nome del file scelto (senza \"\"): ";
        std::cin >> result;
    }

    return result;
}