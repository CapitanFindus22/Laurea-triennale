#include "CSVFile.hpp"

// Open the file
CSVFile::CSVFile(std::string name)
{

  file.open("../csv/" + name);

  if (!file.is_open())
  {
    std::cout << "Error opening file";
    exit(-1);
  }

  file_name = name;

  std::string str;

  // Get the delimiter
  std::getline(file, str);
  delimiter = str[0];

  // Get the number of columns
  std::getline(file, str);
  num_columns = 1;

  for (char &c : str)
  {

    if (c == delimiter)
    {
      num_columns++;
    }
  }
}

// Close the file
CSVFile::~CSVFile()
{

  file.close();
}

// Get the name file
std::string CSVFile::getName()
{
  return file_name;
}

// Get the next line
std::string CSVFile::getline()
{

  std::string str;

  std::getline(file, str);

  return str;
}
