#include "main.hpp"

// Generate SQL commands to save the values in the DB
void log2db(Con2DB db1, size_t numStream, std::string fileName)
{
  // Buffer
  char sqlcmd[1000];

  // Complete command
  PGresult *res;

  // BEGIN
  sprintf(sqlcmd, "BEGIN");

  res = db1.ExecSQLcmd(sqlcmd);
  PQclear(res);

  // INSERT
  sprintf(sqlcmd,
          "INSERT INTO session_info (data_inizio,nome_file,numero_stream) VALUES (NOW(), \'%s\', %zu) ON CONFLICT DO NOTHING",
          fileName.c_str(),
          numStream);

  res = db1.ExecSQLcmd(sqlcmd);
  PQclear(res);

  // COMMIT
  sprintf(sqlcmd, "COMMIT");

  res = db1.ExecSQLcmd(sqlcmd);

  PQclear(res);
}

int logfromdb(Con2DB db1, std::string fileName)
{
  // Buffer
  char sqlcmd[1000];
  PGresult *res;

  sprintf(sqlcmd,
          "SELECT * FROM session_info WHERE nome_file=\'%s\' AND id = (SELECT MAX(id) FROM session_info)",
          fileName.c_str());

  res = db1.ExecSQLtuples(sqlcmd);

  int result = std::stoi(PQgetvalue(res, 0, PQfnumber(res, "id")));

  std::cout << result;

  PQclear(res);

  return result;
}