#include "main.hpp"

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

    // Connect to Redis
    redisContext *c = redisConnect(IP, PORT);
    redisReply *r;

    // Close the connection
    redisFree(c);

    // deque

    return 0;
}
