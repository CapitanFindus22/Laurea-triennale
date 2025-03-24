#include "main.hpp"

// Get last entry for StreamName1 (mean) or StreamName1-StreamName2 (Covariance)
double logfromdb(Con2DB &db1, std::string StreamName1, std::string StreamName2, bool fromMean, int id)
{
  // Buffer
  char sqlcmd[1000];

  // The result of the command
  PGresult *res;

  std::string Query;

  double result;

  if (fromMean)
  {
    Query = "SELECT valore FROM media WHERE nome_stream=\'%s\' AND s_id=%d ORDER BY data_ora DESC";
    sprintf(sqlcmd, Query.c_str(), StreamName1.c_str(), id);
  }
  else
  {
    Query = "SELECT * FROM covarianza WHERE nome_stream_1=\'%s\' AND nome_stream_2=\'%s\' AND s_id=%d ORDER BY data_ora DESC";
    sprintf(sqlcmd, Query.c_str(), StreamName1.c_str(), StreamName2.c_str(), id);
  }

  res = db1.ExecSQLtuples(sqlcmd);

  result = std::stod(PQgetvalue(res, 0, PQfnumber(res, "valore")));

  PQclear(res);

  return result;
}

/*Get last entry for StreamName1 (mean) or StreamName1-StreamName2 (Covariance) and
 calculate the time passed before sending the alert */
double logfromdb_time(Con2DB &db1, std::string StreamName1, std::string StreamName2, bool fromMean, int id)
{
  // Buffer
  char sqlcmd[1000];

  // The result of the command
  PGresult *res;

  std::string Query = "SELECT EXTRACT(EPOCH FROM (SELECT MAX(data_evento) FROM alerts WHERE nome_stream=\'%s\' AND s_id=%d) - ";

  double result;

  if (fromMean)
  {
    Query += "(SELECT MAX(data_ora) FROM media WHERE nome_stream=\'%s\' AND s_id=%d)) AS tempo";
    sprintf(sqlcmd, Query.c_str(), StreamName1.c_str(), id, StreamName1.c_str(), id);
  }
  else
  {
    Query += "(SELECT MAX(data_ora) FROM covarianza WHERE nome_stream_1=\'%s\' AND nome_stream_2=\'%s\' AND s_id=%d)) AS tempo";
    sprintf(sqlcmd, Query.c_str(), (StreamName1 + "-" + StreamName2).c_str(), id, StreamName1.c_str(), StreamName2.c_str(), id);
  }

  res = db1.ExecSQLtuples(sqlcmd);

  result = std::stod(PQgetvalue(res, 0, PQfnumber(res, "tempo")));

  PQclear(res);

  return result;
}

// Get last entry for StreamName (Alert)
double logfromdb_alert(Con2DB &db1, std::string StreamName, int id)
{
  // Buffer
  char sqlcmd[1000];

  // The result of the command
  PGresult *res;

  std::string Query;

  double result;

  Query = "SELECT differenza FROM alerts WHERE nome_stream=\'%s\' AND s_id=%d ORDER BY data_evento DESC";
  sprintf(sqlcmd, Query.c_str(), StreamName.c_str(), id);

  res = db1.ExecSQLtuples(sqlcmd);

  result = std::stod(PQgetvalue(res, 0, PQfnumber(res, "differenza")));

  PQclear(res);

  return result;
}

// Insert outcome of the control in the DB
void log2db(Con2DB &db1, bool outcome, bool isMean, std::string StreamName1, std::string StreamName2, int id)
{
  // Buffer
  char sqlcmd[1000];

  // The result of the command
  PGresult *res;

  // INSERT
  sprintf(sqlcmd,
          "INSERT INTO log_monitor (s_id,data_controllo,nome_stream,tipo,esito) VALUES (%d ,NOW(), \'%s\', \'%s\',\'%s\')",
          id,
          (isMean) ? StreamName1.c_str() : (StreamName1 + "-" + StreamName2).c_str(),
          (isMean) ? "Media" : "Covarianza",
          (outcome) ? "Ok" : "Errore");

  res = db1.ExecSQLcmd(sqlcmd);

  PQclear(res);
}

// Insert time passed before sending the alert in the DB
void log2db_time(Con2DB &db1, bool isMean, std::string StreamName1, std::string StreamName2, int id, double time)
{
  // Buffer
  char sqlcmd[1000];

  // The result of the command
  PGresult *res;

  std::string outcome;

  // Check time
  if (isMean)
  {
    outcome = (time <= 0.5) ? "Ok" : "Errore";
  }
  else
  {
    outcome = (time <= 1) ? "Ok" : "Errore";
  }

  sprintf(sqlcmd,
          "INSERT INTO log_monitor (s_id,data_controllo,nome_stream,tipo,esito,tempo_trascorso) VALUES (%d ,NOW(), \'%s\', \'%s\', \'%s\', %f)",
          id,
          (isMean) ? StreamName1.c_str() : (StreamName1 + "-" + StreamName2).c_str(),
          (isMean) ? "Tempo_m" : "Tempo_c",
          outcome.c_str(),
          time);

  res = db1.ExecSQLcmd(sqlcmd);

  PQclear(res);
}

// Insert outcome of the control in the DB (Alert)
void log2db_alert(Con2DB &db1, bool outcome, std::string StreamName, int id)
{
  // Buffer
  char sqlcmd[1000];

  // The result of the command
  PGresult *res;

  sprintf(sqlcmd,
          "INSERT INTO log_monitor (s_id,data_controllo,nome_stream,tipo,esito) VALUES (%d ,NOW(), \'%s\', \'%s\',\'%s\')",
          id,
          StreamName.c_str(),
          "Alert_m",
          (outcome) ? "Ok" : "Errore");

  res = db1.ExecSQLcmd(sqlcmd);

  PQclear(res);
}