#!/bin/bash

# Elenco degli stream da svuotare
streams_to_trim=("INFOSTREAM" "MMonitor" "TMonitor" "AMonitor" "CMonitor" "CTMonitor")

# Aggiungi gli stream che corrispondono al pattern STREAM<number>
streams=$(redis-cli --scan --pattern 'STREAM*')
streams_dash=$(redis-cli --scan --pattern 'STREAM_*')
streams_m=$(redis-cli --scan --pattern 'M*')

# Aggiungi stream al nostro array se corrispondono ai pattern
for stream in $streams $streams_dash $streams_m; do
    # Controlla se è uno stream
    if [[ $(redis-cli TYPE "$stream") == "stream" ]]; then
        streams_to_trim+=("$stream")
    fi
done

# Per ogni stream nell'elenco, svuota i messaggi
for stream in "${streams_to_trim[@]}"; do
    if [[ $(redis-cli TYPE "$stream") == "stream" ]]; then
        redis-cli XTRIM "$stream" MAXLEN 0 >/dev/null 2>&1 # Ignora output e errori
        echo "Messaggi rimossi da: $stream"
    fi
done

echo "Operazione completata."
