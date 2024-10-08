#include "main.hpp"

std::mutex dbMutex;

//Generate SQL commands to save the values in the DB
void log2db(Con2DB db1, float value, std::string streamName)
{
  //Buffer
  char sqlcmd[1000];

  //Complete command
  PGresult *res;

  //BEGIN
  sprintf(sqlcmd, "BEGIN");

  std::lock_guard<std::mutex> lock(dbMutex);

  {
    res = db1.ExecSQLcmd(sqlcmd);
    PQclear(res);

    //INSERT
    sprintf(sqlcmd,
            "INSERT INTO LogTable VALUES (NOW(), %ld, %f, %d, %.4f, \'%s\') ON CONFLICT DO NOTHING",
            2L,
            value,
            1,
            0.0,
            streamName.c_str());

    res = db1.ExecSQLcmd(sqlcmd);
    PQclear(res);

    //COMMIT
    sprintf(sqlcmd, "COMMIT");
    
      
      res = db1.ExecSQLcmd(sqlcmd);
  }
  PQclear(res);
}