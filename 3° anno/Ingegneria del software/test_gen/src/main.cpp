#include "main.hpp"
#include "CSVFile.hpp"

/*
    This program read data from a csv file, create a stream for each column
    and send one row of values from the file to its associated stream
*/
int main()
{

#if (DEBUG > 0)
    setvbuf(stdout, (char *)NULL, _IONBF, 0);
    setvbuf(stderr, (char *)NULL, _IONBF, 0);
#endif

    // Connect to the DB
    Con2DB db1(IP, PORT_DB, USERNAME, PASSWORD, DB_NAME);

    // Connect to Redis
    redisContext *c = redisConnect(IP, PORT);

    // Choose the csv file
    std::string CSVName = ChooseFile();
    CSVFile f(CSVName);

    // Create log for this session and get the id
    log2db(std::ref(db1), f.num_columns, CSVName);
    int id = logfromdb(std::ref(db1), f.getName());

    // Each stream will be called STREAMnumber
    // Example: the first column will be sent to STREAM0
    std::string baseName = "STREAM";
    std::string names[f.num_columns];

    // Counter
    size_t i;

    // Array that will contain a row of values
    double values[f.num_columns];

    // Basically simulation time
    const size_t rowsToSend = SetNum();
    size_t rowsSent = 0;

    // Create the Stream names and clean them
    for (i = 0; i < f.num_columns; i++)
    {
        names[i] = baseName + std::to_string(i);
    }

    std::cout << "Sessione n°" << id << std::endl;

    // Send infos to the other components
    SendMessage(c, std::to_string(f.num_columns), ISTREAM);
    SendMessage(c, std::to_string(id), ISTREAM);
    SendMessage(c, std::to_string(rowsToSend), ISTREAM);

    // Read a line from the file and send it through the streams
    while (rowsSent < rowsToSend)
    {

        // Convert the row to floats
        String2DoubleArray(f.getline(), f.delimiter, values);

        // Send the values
        for (i = 0; i < f.num_columns; i++)
        {
            SendMessage(c, std::to_string(values[i]), names[i]);
            std::cout << "Invio: " << values[i] << " a " << names[i] << "\n"
                      << std::endl;
        }

        rowsSent++;
    }

    std::cout << "Fine simulazione" << std::endl;

    // Close the connection
    redisFree(c);
    db1.finish();

    return 0;
}