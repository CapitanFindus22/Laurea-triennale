#include "main.hpp"

class CSVFile
{
private:
    // The file to open and its name
    std::ifstream file;
    std::string file_name;

public:
    // The delimiter used between the columns
    char delimiter;

    // The number of columns
    size_t num_columns;

    CSVFile(std::string name);
    ~CSVFile();

    std::string getName();

    std::string getline();
};
