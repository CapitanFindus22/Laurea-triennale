#include "main.hpp"

// Insert mean value in the db
void log2db(Con2DB &db1, std::string value, std::string StreamName, int id)
{
  // Buffer
  char sqlcmd[1000];

  // The result of the command
  PGresult *res;

  // INSERT
  sprintf(sqlcmd,
          "INSERT INTO media (s_id,data_ora,nome_stream,valore) VALUES (%d ,NOW(), \'%s\', %s)",
          id,
          StreamName.c_str(),
          value.c_str());

  res = db1.ExecSQLcmd(sqlcmd);

  PQclear(res);
}

// Insert alert in the DB
void logAlert(Con2DB &db1, std::string value, std::string StreamName, int id)
{
  // Buffer
  char sqlcmd[1000];

  // The result of the command
  PGresult *res;

  // INSERT
  sprintf(sqlcmd,
          "INSERT INTO alerts (s_id,data_evento,nome_stream,tipo,differenza) VALUES (%d ,NOW(), \'%s\',\'%s\', %s)",
          id,
          StreamName.c_str(),
          "Media",
          value.c_str());

  res = db1.ExecSQLcmd(sqlcmd);

  PQclear(res);
}

// Get last entry for StreamName
double logfromdb(Con2DB &db1, std::string StreamName, int id)
{
  // Buffer
  char sqlcmd[1000];

  // The result of the command
  PGresult *res;

  double result;

  sprintf(sqlcmd,
          "SELECT valore FROM media WHERE nome_stream=\'%s\' AND s_id=%d ORDER BY data_ora DESC",
          StreamName.c_str(),
          id);

  res = db1.ExecSQLtuples(sqlcmd);

  result = std::stof(PQgetvalue(res, 1, PQfnumber(res, "valore")));

  PQclear(res);

  return result;
}