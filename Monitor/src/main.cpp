#include "main.hpp"


int main()
{
    #if (DEBUG > 0)
        setvbuf(stdout, (char *)NULL, _IONBF, 0);
        setvbuf(stderr, (char *)NULL, _IONBF, 0);
    #endif

    std::thread threads[5];

    threads[0] = std::thread(MM);
    threads[1] = std::thread(MC);
    threads[2] = std::thread(MMT);
    threads[3] = std::thread(MCT);

    for (std::thread &t : threads)
    {
        t.join();
    }

    return 0;
}