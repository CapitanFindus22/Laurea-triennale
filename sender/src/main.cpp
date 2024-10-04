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
    std::string baseName = "STREAM_";
    std::string currentName;
    size_t i = 0;

    // Open the csv file with name CSVName
    std::string file_name = CSVName;
    CSVFile f(file_name);

    // Array that will contain a row of values
    float values[f.num_columns];
    size_t arr_size = f.num_columns;

    // Connect to Redis
    redisContext *c = redisConnect(IP, PORT);
    redisReply *r;

    // Create the Streams
    for (i = 1; i <= arr_size; i++)
    {
        initStreams(c, GenerateStreamName(baseName, i).c_str());
    }

    // Read a line from the file and send it through the streams
    while (1)
    {

        String2FloatArray(f.getline(), f.delimiter, values, arr_size);
        i = 1;

        for (float val : values)
        {
            currentName = GenerateStreamName(baseName, i);
            r = RedisCommand(c, "XADD %s * value mem:%f", currentName.c_str(), val);
            assertReplyType(c, r, REDIS_REPLY_STRING);
            printf("Added value -> mem:%f (id: %s) %s \n", val, r->str, currentName.c_str());
            freeReplyObject(r);
            i++;
        }

        assert(i - 1 == arr_size);
        printf("\n");
        sleep(1);
    }

    // Close the connection
    redisFree(c);

    // deque

    return 0;
}
