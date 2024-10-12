#include "main.hpp"
#include "CSVFile.hpp"

/*
    This program read data from a csv file, create a stream for each column
    and send one row of values from the file to its own stream
*/
int main()
{

#if (DEBUG > 0)
    setvbuf(stdout, (char *)NULL, _IONBF, 0);
    setvbuf(stderr, (char *)NULL, _IONBF, 0);
#endif

    Con2DB db1(IP, PORT_DB, USERNAME, PASSWORD, DB_NAME);

    // Each stream will be called STREAM_number
    // Example: the first column will be attached to STREAM1
    std::string baseName = "STREAM";
    size_t i;

    // Open the csv file with name CSVName
    std::string CSVName = "winequality-red.csv";
    CSVFile f(CSVName);

    // Array that will contain a row of values
    float values[f.num_columns];
    size_t arr_size = f.num_columns;

    log2db(db1, f.num_columns, CSVName);
    int id = logfromdb(db1, f.getName());

    // Connect to Redis
    redisContext *c = redisConnect(IP, PORT);
    redisReply *r;

    RedisCommand(c, "XTRIM INFOSTREAM 0");

    std::string names[f.num_columns];

    // Create the Streams
    for (i = 0; i < arr_size; i++)
    {
        names[i] = baseName + std::to_string(i);
        RedisCommand(c, "XTRIM %s 0", names[i].c_str());

    }

    SendMessage(c, arr_size, "INFOSTREAM");
    SendMessage(c, (float)id, "INFOSTREAM");

    // Read a line from the file and send it through the streams
    while (1)
    {

        String2FloatArray(f.getline(), f.delimiter, values, arr_size);
        
        for (i = 0; i < f.num_columns; i++)
        {
            SendMessage(c,values[i],names[i]);
        }

        sleep(2);
    }

    // Close the connection
    redisFree(c);

    // deque

    return 0;
}
