\set dbname log_anomalies
\set username monitor

-- Crea il database
DROP DATABASE IF EXISTS :dbname;
CREATE DATABASE :dbname;

-- Connessione al database come utente postgres
\c :dbname postgres

-- Elimina il ruolo se già esistente
REASSIGN OWNED BY :username TO postgres;
REVOKE ALL PRIVILEGES ON ALL TABLES IN SCHEMA public FROM :username;
REVOKE ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public FROM :username;
REVOKE ALL PRIVILEGES ON ALL FUNCTIONS IN SCHEMA public FROM :username;
DROP OWNED BY :username;
DROP USER IF EXISTS :username;
CREATE USER :username WITH ENCRYPTED PASSWORD '65568162';

-- Connessione di nuovo al database
\c :dbname postgres

-- Concedere privilegi all'utente
GRANT ALL PRIVILEGES ON DATABASE :dbname TO :username;
GRANT ALL ON SCHEMA public TO :username;
