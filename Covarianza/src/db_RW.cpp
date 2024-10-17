#include "main.hpp"

// Generate SQL commands to save the values in the DB
void log2db(Con2DB &db1, double value, std::string StreamName1, std::string StreamName2, int id)
{
  // Buffer
  char sqlcmd[1000];

  // The result of the command
  PGresult *res;

  // INSERT
  sprintf(sqlcmd,
          "INSERT INTO covarianza (s_id,data_ora,nome_stream_1,nome_stream_2,valore) VALUES (%d ,NOW(), \'%s\',\'%s\', %f)",
          id,
          StreamName1.c_str(),
          StreamName2.c_str(),
          value);

  res = db1.ExecSQLcmd(sqlcmd);

  PQclear(res);
}

// Generate SQL commands to save the values in the DB
void logAlert(Con2DB &db1, double value, std::string StreamName1, std::string StreamName2, int id)
{
  // Buffer
  char sqlcmd[1000];

  // The result of the command
  PGresult *res;

  // INSERT
  sprintf(sqlcmd,
          "INSERT INTO alerts (s_id,data_evento,nome_stream,tipo,differenza) VALUES (%d ,NOW(), \'%s\',\'%s\', %f)",
          id,
          (StreamName1 + "-" + StreamName2).c_str(),
          "Covarianza",
          value);

  res = db1.ExecSQLcmd(sqlcmd);

  PQclear(res);
}

double logfromdb(Con2DB &db1, std::string StreamName1, std::string StreamName2)
{
  // Buffer
  char sqlcmd[1000];

  // The result of the command
  PGresult *res;

  double result;

  sprintf(sqlcmd,
          "SELECT * FROM covarianza WHERE nome_stream_1=\'%s\' AND nome_stream_2=\'%s\' ORDER BY data_ora DESC",
          StreamName1.c_str(),
          StreamName2.c_str());

  res = db1.ExecSQLtuples(sqlcmd);

  result = std::stof(PQgetvalue(res, 1, PQfnumber(res, "valore")));

  PQclear(res);

  return result;
}