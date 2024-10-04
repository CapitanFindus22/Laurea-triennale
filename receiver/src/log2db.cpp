#include "main2.hpp"

void log2db(Con2DB db1, float value)
{

  char sqlcmd[1000];

  PGresult *res;

  sprintf(sqlcmd, "BEGIN");
  res = db1.ExecSQLcmd(sqlcmd);
  PQclear(res);

  sprintf(sqlcmd,
          "INSERT INTO LogTable VALUES (\'%s\', %ld, %d, %d, %f, \'%s\') ON CONFLICT DO NOTHING",
          "2020-10-10",
          2,
          3,
          1,
          value,
          "GREEN");

  res = db1.ExecSQLcmd(sqlcmd);
  PQclear(res);

  sprintf(sqlcmd, "COMMIT");
  res = db1.ExecSQLcmd(sqlcmd);
  PQclear(res);
}