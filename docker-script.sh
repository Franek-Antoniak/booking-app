#!/bin/bash

# Start the Docker Compose configuration
echo "Starting Docker Compose configuration..."
docker-compose up -d

# Wait until all containers are up and running
echo "Waiting for containers to start..."
docker-compose wait

echo "Docker Compose configuration started."