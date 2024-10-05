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

    // Each stream will be called STREAM_number
    // Example: the first column will be attached to STREAM_1
    std::string baseName = "STREAM";
    std::string currentName;
    size_t i;

    // Open the csv file with name CSVName
    std::string CSVName = "Customer_Churn.csv";
    CSVFile f(CSVName);

    // Array that will contain a row of values
    float values[f.num_columns];
    size_t arr_size = f.num_columns;

    // Connect to Redis
    redisContext *c = redisConnect(IP, PORT);

    // Create the Streams
    for (i = 0; i <= arr_size; i++)
    {
        initStreams(c, GenerateStreamName(baseName, i).c_str());
    }

    SendMessage(c,arr_size,"STREAM0");

    sleep(1);

    // Read a line from the file and send it through the streams
    while (1)
    {

        String2FloatArray(f.getline(), f.delimiter, values, arr_size);
        i = 1;

        for (float val : values)
        {
            SendMessage(c,val,GenerateStreamName(baseName, i).c_str());
            printf("Added value -> mem:%f to %s \n", val, GenerateStreamName(baseName, i).c_str());
            i++;
            
        }

        sleep(2);
        assert(i - 1 == arr_size);
        printf("\n");
    }

    // Close the connection
    redisFree(c);

    // deque

    return 0;
}
