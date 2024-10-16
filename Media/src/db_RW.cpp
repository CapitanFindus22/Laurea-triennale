#include "main.hpp"

// Generate SQL commands to save the values in the DB
void log2db(Con2DB &db1, std::string value, std::string streamName, int id)
{
  // Buffer
  char sqlcmd[1000];

  // Complete command
  PGresult *res;

  // INSERT
  sprintf(sqlcmd,
          "INSERT INTO media (s_id,data_ora,nome_stream,valore) VALUES (%d ,NOW(), \'%s\', %s)",
          id,
          streamName.c_str(),
          value.c_str());

  res = db1.ExecSQLcmd(sqlcmd);

  PQclear(res);
}

// Generate SQL commands to save the values in the DB
void logAlert(Con2DB &db1, std::string value, std::string streamName, int id)
{
  // Buffer
  char sqlcmd[1000];

  // Complete command
  PGresult *res;

  // INSERT
  sprintf(sqlcmd,
          "INSERT INTO alerts (s_id,data_evento,nome_stream,tipo,differenza) VALUES (%d ,NOW(), \'%s\',\'%s\', %s)",
          id,
          streamName.c_str(),
          "Media",
          value.c_str());

  res = db1.ExecSQLcmd(sqlcmd);

  PQclear(res);
}

float logfromdb(Con2DB &db1, std::string streamName)
{
  // Buffer
  char sqlcmd[1000];
  PGresult *res;

  sprintf(sqlcmd,
          "SELECT valore FROM media WHERE nome_stream=\'%s\' ORDER BY data_ora DESC",
          streamName.c_str());

  res = db1.ExecSQLtuples(sqlcmd);

  float result = std::stof(PQgetvalue(res, 1, PQfnumber(res, "valore")));

  PQclear(res);

  return result;
}