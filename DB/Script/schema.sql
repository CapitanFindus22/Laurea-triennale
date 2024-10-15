\c :dbname 

CREATE DOMAIN TimePoint AS timestamp;
CREATE DOMAIN StreamName AS VARCHAR(30);
CREATE DOMAIN Note AS VARCHAR(200);
CREATE DOMAIN val AS double precision;
CREATE DOMAIN posint AS INTEGER CHECK (VALUE > 0);
CREATE TYPE ALERT_TYPE AS ENUM ('Media', 'Covarianza', 'Altro');
CREATE TYPE OUTCOME AS ENUM ('Ok', 'Errore');

CREATE TABLE IF NOT EXISTS session_info (
        id serial PRIMARY KEY,
        Data_inizio TimePoint NOT NULL,
        Nome_file Note NOT NULL,
        Numero_stream posint NOT NULL,
        Descrizione Note
);

CREATE TABLE IF NOT EXISTS media (
        s_id posint NOT NULL,
        Data_ora TimePoint NOT NULL,
        Nome_stream StreamName NOT NULL,
        Valore val NOT NULL,
        FOREIGN KEY (s_id) REFERENCES session_info(id),
        PRIMARY KEY (s_id, Data_ora, Nome_stream)
);

CREATE TABLE IF NOT EXISTS covarianza (
        s_id posint NOT NULL,
        Data_ora TimePoint NOT NULL,
        Nome_stream_1 StreamName NOT NULL,
        Nome_stream_2 StreamName NOT NULL,
        Valore val NOT NULL,
        FOREIGN KEY (s_id) REFERENCES session_info(id),
        PRIMARY KEY (s_id, Data_ora, Nome_stream_1, Nome_stream_2)
);

CREATE TABLE IF NOT EXISTS alerts (
        id serial PRIMARY KEY,
        s_id posint NOT NULL,
        Data_evento TimePoint NOT NULL,
        Nome_stream StreamName NOT NULL,
        Tipo ALERT_TYPE NOT NULL,
        Differenza val,
        FOREIGN KEY (s_id) REFERENCES session_info(id)
);

CREATE TABLE IF NOT EXISTS log_monitor (
        id serial PRIMARY KEY,
        s_id posint NOT NULL,
        Data_controllo TimePoint NOT NULL,
        Nome_stream StreamName NOT NULL,
        Tipo ALERT_TYPE NOT NULL,
        Esito OUTCOME,
        Tempo_trascorso val,
        FOREIGN KEY (s_id) REFERENCES session_info(id)
);