\c :dbname postgres

-- user already exists
GRANT ALL PRIVILEGES ON DATABASE :dbname to :username ;


ALTER TABLE session_info OWNER TO :username ;
ALTER TABLE media OWNER TO :username ;
ALTER TABLE covarianza OWNER TO :username ;
ALTER TABLE alerts OWNER TO :username ;

-- grant all privileges on all tables in schema public to :username ;
-- grant all privileges on all sequences in schema public to :username ;

GRANT ALL ON SCHEMA public TO :username ;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO :username ;
