#!/bin/bash
set -e
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
  CREATE DATABASE customer_db;
  CREATE DATABASE business_db;
  CREATE DATABASE inventory_db;
  CREATE DATABASE order_db;
EOSQL
