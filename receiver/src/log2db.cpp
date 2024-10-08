#include "main.hpp"

std::mutex dbMutex;

//Generate SQL commands to save the values in the DB
void log2db(Con2DB db1, float value, std::string streamName,int id)
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
            "INSERT INTO media (s_id,data_ora,nome_stream,valore) VALUES (%d ,NOW(), \'%s\', %f)",
            id,
            streamName.c_str(),
            value);

    res = db1.ExecSQLcmd(sqlcmd);
    PQclear(res);

    //COMMIT
    sprintf(sqlcmd, "COMMIT");
    
      res = db1.ExecSQLcmd(sqlcmd);
  }
  PQclear(res);
}