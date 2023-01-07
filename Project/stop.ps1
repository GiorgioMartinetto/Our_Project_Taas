#!/bin/bash
docker compose down
docker image prune -f
docker volume prune -f