#include "main.hpp"

// Generate SQL commands to save the values in the DB
void log2db(Con2DB& db1, double value, std::string streamName1, std::string streamName2, int id)
{
  // Buffer
  char sqlcmd[1000];

  // Complete command
  PGresult *res;

    // INSERT
    sprintf(sqlcmd,
            "INSERT INTO covarianza (s_id,data_ora,nome_stream_1,nome_stream_2,valore) VALUES (%d ,NOW(), \'%s\',\'%s\', %f)",
            id,
            streamName1.c_str(),
            streamName2.c_str(),
            value);

    res = db1.ExecSQLcmd(sqlcmd);
  

  PQclear(res);
}

// Generate SQL commands to save the values in the DB
void logAlert(Con2DB& db1, float value, std::string streamName1, std::string streamName2, int id)
{
  // Buffer
  char sqlcmd[1000];

  // Complete command
  PGresult *res;

    // INSERT
    sprintf(sqlcmd,
            "INSERT INTO alerts (s_id,data_evento,nome_stream,tipo,differenza) VALUES (%d ,NOW(), \'%s\',\'%s\', %f)",
            id,
            (streamName1+"-"+streamName2).c_str(),
            "Covarianza",
            value);

    res = db1.ExecSQLcmd(sqlcmd);
  

  PQclear(res);
}

float logfromdb(Con2DB& db1, std::string streamName1, std::string streamName2)
{
  // Buffer
  char sqlcmd[1000];
  PGresult *res;

  sprintf(sqlcmd,
          "SELECT * FROM covarianza WHERE nome_stream_1=\'%s\' AND nome_stream_2=\'%s\' ORDER BY data_ora DESC",
          streamName1.c_str(),
          streamName2.c_str());

  res = db1.ExecSQLtuples(sqlcmd);
  
  float result = std::stof(PQgetvalue(res, 0, PQfnumber(res, "valore")));

  std::cout << streamName1 + "-" + streamName2 << " " << result << std::endl;

  PQclear(res);

  return result;
}