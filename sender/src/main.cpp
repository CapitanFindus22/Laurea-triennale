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
    // Example: the first column will be attached to STREAM_1
    std::string baseName = "STREAM";
    std::string currentName;
    size_t i;

    // Open the csv file with name CSVName
    std::string CSVName = "winequality-red.csv";
    CSVFile f(CSVName);

    // Array that will contain a row of values
    float values[f.num_columns];
    size_t arr_size = f.num_columns;

    log2db(db1,f.num_columns,CSVName);
    int id = logfromdb(db1,f.getName());

    // Connect to Redis
    redisContext *c = redisConnect(IP, PORT);

    std::string names[f.num_columns];

    // Create the Streams
    for (i = 0; i < arr_size; i++)
    {
        names[i] = GenerateStreamName(baseName, i);
        initStreams(c, names[i].c_str());
        redisReply* r = RedisCommand(c, "XTRIM %s MINID %d", names[i].c_str(), 0);
        assertReplyType(c, r, REDIS_REPLY_INTEGER);
        freeReplyObject(r);
    }

    initStreams(c, "INFOSTREAM");
    RedisCommand(c, "XTRIM %s MINID %d", "INFOSTREAM", 0);
    SendMessage(c,arr_size,"INFOSTREAM");
    sleep(1);
    SendMessage(c,(float)id,"INFOSTREAM");

    // Read a line from the file and send it through the streams
    while (1)
    {

        String2FloatArray(f.getline(), f.delimiter, values, arr_size);
        i = 0;

        for (float val : values)
        {
            SendMessage(c,val,names[i]);
            std::cout << "Added value -> mem: " << val << " to " << names[i] << std::endl;
            i++;
            
        }

        sleep(2);
        printf("\n");
    }

    // Close the connection
    redisFree(c);

    // deque

    return 0;
}
