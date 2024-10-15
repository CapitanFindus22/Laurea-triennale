#include "main.hpp"

// Generate SQL commands to save the values in the DB
void log2db(Con2DB& db1, bool outcome, bool isMean, std::string streamName1, std::string streamName2, int id)
{
  // Buffer
  char sqlcmd[1000];

  // Complete command
  PGresult *res;

  // INSERT
  sprintf(sqlcmd,
          "INSERT INTO log_monitor (s_id,data_controllo,nome_stream,tipo,esito) VALUES (%d ,NOW(), \'%s\', \'%s\',\'%s\')",
          id,
          (isMean)?streamName1.c_str():(streamName1+"-"+streamName2).c_str(),
          (isMean)?"Media":"Covarianza",
          (outcome)?"Ok":"Errore");

  res = db1.ExecSQLcmd(sqlcmd);

  PQclear(res);
}

float logfromdb(Con2DB& db1, std::string streamName1, std::string streamName2, bool fromMean)
{
  // Buffer
  char sqlcmd[1000];
  PGresult *res;

  if (fromMean)
  {
      sprintf(sqlcmd,"SELECT valore FROM media WHERE nome_stream=\'%s\' ORDER BY data_ora DESC",streamName1.c_str());
  }
  
  else
  {
    
    sprintf(sqlcmd,"SELECT * FROM covarianza WHERE nome_stream_1=\'%s\' AND nome_stream_2=\'%s\' ORDER BY data_ora DESC",
            streamName1.c_str(),
            streamName2.c_str());

  }

  res = db1.ExecSQLtuples(sqlcmd);

  float result = std::stof(PQgetvalue(res, 0, PQfnumber(res, "valore")));

  PQclear(res);

  return result;
}