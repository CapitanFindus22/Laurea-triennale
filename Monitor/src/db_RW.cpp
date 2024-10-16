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

double logfromdb(Con2DB& db1, std::string streamName1, std::string streamName2, bool fromMean,int id)
{
  // Buffer
  char sqlcmd[1000];
  PGresult *res;

  std::string baseQuery;

  if (fromMean) {
      baseQuery = "SELECT valore FROM media WHERE nome_stream=\'%s\' AND s_id=%d ORDER BY data_ora DESC";
      sprintf(sqlcmd, baseQuery.c_str(), streamName1.c_str(),id);
  } else {
      baseQuery = "SELECT * FROM covarianza WHERE nome_stream_1=\'%s\' AND nome_stream_2=\'%s\' AND s_id=%d ORDER BY data_ora DESC";
      sprintf(sqlcmd, baseQuery.c_str(), streamName1.c_str(), streamName2.c_str(),id);
  }


  res = db1.ExecSQLtuples(sqlcmd);

  double result = std::stod(PQgetvalue(res, 0, PQfnumber(res, "valore")));

  PQclear(res);

  return result;
}

void log2db_time(Con2DB& db1, bool isMean, std::string streamName1, std::string streamName2, int id , double time)
{
  // Buffer
  char sqlcmd[1000];

  // Complete command
  PGresult *res;

  // INSERT
  sprintf(sqlcmd,
          "INSERT INTO log_monitor (s_id,data_controllo,nome_stream,tipo,Tempo_trascorso) VALUES (%d ,NOW(), \'%s\', \'%s\',%f)",
          id,
          (isMean)?streamName1.c_str():(streamName1+"-"+streamName2).c_str(),
          (isMean)?"Tempo_m":"Tempo_c",
          time);

  res = db1.ExecSQLcmd(sqlcmd);

  PQclear(res);
}

double logfromdb_time(Con2DB& db1, std::string streamName1, std::string streamName2, bool fromMean,int id)
{
  // Buffer
  char sqlcmd[1000];
  PGresult *res;

  std::string baseQuery = "SELECT EXTRACT(EPOCH FROM (SELECT MAX(data_evento) FROM alerts WHERE nome_stream=\'%s\' AND s_id=%d) - ";

  if (fromMean) {
      baseQuery += "(SELECT MAX(data_ora) FROM media WHERE nome_stream=\'%s\' AND s_id=%d)) AS tempo";
      sprintf(sqlcmd, baseQuery.c_str(), streamName1.c_str(), id,streamName1.c_str(),id);
  } else {
      baseQuery += "(SELECT MAX(data_ora) FROM covarianza WHERE nome_stream_1=\'%s\' AND nome_stream_2=\'%s\' AND s_id=%d)) AS tempo";
      sprintf(sqlcmd, baseQuery.c_str(), (streamName1 + "-" + streamName2).c_str(), id,streamName1.c_str(), streamName2.c_str(),id);
  }


  res = db1.ExecSQLtuples(sqlcmd);

  double result = std::stod(PQgetvalue(res, 0, PQfnumber(res, "tempo")));

  PQclear(res);

  return result;
}

double logfromdb_alert(Con2DB& db1, std::string streamName, int id)
{
  // Buffer
  char sqlcmd[1000];
  PGresult *res;

  std::string baseQuery;

  baseQuery = "SELECT differenza FROM alerts WHERE nome_stream=\'%s\' AND s_id=%d ORDER BY data_evento DESC";
  sprintf(sqlcmd, baseQuery.c_str(), streamName.c_str(),id);

  res = db1.ExecSQLtuples(sqlcmd);

  double result = std::stod(PQgetvalue(res, 0, PQfnumber(res, "differenza")));

  PQclear(res);

  return result;
}

void log2db_alert(Con2DB& db1, bool outcome, std::string streamName, int id)
{
  // Buffer
  char sqlcmd[1000];

  // Complete command
  PGresult *res;

  // INSERT
  sprintf(sqlcmd,
          "INSERT INTO log_monitor (s_id,data_controllo,nome_stream,tipo,esito) VALUES (%d ,NOW(), \'%s\', \'%s\',\'%s\')",
          id,
          streamName.c_str(),
          "Alert_m",
          (outcome)?"Ok":"Errore");

  res = db1.ExecSQLcmd(sqlcmd);

  PQclear(res);
}