@echo off
setlocal enabledelayedexpansion

set services=api-gateway account-service kafka-service ledger-service payment-service wallet-service

cd ..
for %%s in (%services%) do (
    echo Starting %%s ...
    cd %%s
    docker compose up -d --build
    cd ..
)

echo All services started.
pause