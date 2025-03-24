# Anomaly Detection

Sistema di rilevamento anomalie per uno stream di dati, progetto per Ingegneria del SW.

Lista dei file:

- build.sh Script per compilare tutti i file e creare il DB
- clean.sh Script per rimuovere eseguibili e file oggetto
- run.sh Script per eseguire tutte le componenti
- clean_stream.sh Script che pulisce i Stream usati dai componenti

Per usare il programma basta eseguire run.sh (dopo aver eseguito build.sh)

Librerie necessarie:

- <postgresql/libpq-fe.h>
- <hiredis/hiredis.h>

DB usato: PostgreSQL
