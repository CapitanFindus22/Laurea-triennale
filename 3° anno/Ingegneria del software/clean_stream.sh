#!/bin/bash

# Streams to clean
streams_to_trim=("INFOSTREAM" "MMonitor" "TMonitor" "AMonitor" "CMonitor" "CTMonitor")

# Pattern of the other streams
streams=$(redis-cli --scan --pattern 'STREAM*')
streams_dash=$(redis-cli --scan --pattern 'STREAM_*')
streams_m=$(redis-cli --scan --pattern 'M*')

# Add streams in the list
for stream in $streams $streams_dash $streams_m; do
    if [[ $(redis-cli TYPE "$stream") == "stream" ]]; then
        streams_to_trim+=("$stream")
    fi
done

# Clean each stream
for stream in "${streams_to_trim[@]}"; do
    if [[ $(redis-cli TYPE "$stream") == "stream" ]]; then
        redis-cli XTRIM "$stream" MAXLEN 0 >/dev/null 2>&1
        echo "Messaggi rimossi da: $stream"
    fi
done

echo "Operazione completata."
