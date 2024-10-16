#include "main.hpp"

// Generate SQL commands to save the values in the DB
void log2db(Con2DB &db1, size_t numStream, std::string fileName)
{
  // Buffer
  char sqlcmd[1000];

  // The result of the command
  PGresult *res;

  // INSERT
  sprintf(sqlcmd,
          "INSERT INTO session_info (data_inizio,nome_file,numero_stream) VALUES (NOW(), \'%s\', %zu) ON CONFLICT DO NOTHING",
          fileName.c_str(),
          numStream);

  res = db1.ExecSQLcmd(sqlcmd);

  PQclear(res);
}

// Get this session ID
int logfromdb(Con2DB &db1, std::string fileName)
{
  // Buffer
  char sqlcmd[1000];

  // The result of the command
  PGresult *res;

  int result;

  sprintf(sqlcmd,
          "SELECT * FROM session_info WHERE nome_file=\'%s\' AND id = (SELECT MAX(id) FROM session_info)",
          fileName.c_str());

  res = db1.ExecSQLtuples(sqlcmd);

  result = std::stoi(PQgetvalue(res, 0, PQfnumber(res, "id")));

  PQclear(res);

  return result;
}