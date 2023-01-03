#!/bin/bash

# Install jq if it is not installed
if ! [ -x "$(command -v jq)" ]; then
    echo "Installing jq..."
    apt-get update && apt-get install jq -y
fi

# Make the GET request to get available screenings
echo "Getting available screenings..."
response=$(curl -X GET "http://localhost:9090/screenings/search?from=2018-01-01T00:00:00")

# Extract the first screeningId from the response
echo "Extracting the first screeningId..."
screeningId=$(echo $response | jq -r '.content[0].screeningId')

# Make the GET request to get available seats for the chosen screening
echo "Getting available seats for the chosen screening..."
availableSeatsResponse=$(curl -X GET "http://localhost:9090/screenings/search/available/$screeningId")

# Extract the first three seatIds from the response
echo "Extracting the first three seatIds from the response..."
seat1=$(echo $availableSeatsResponse | jq -r '.availableSeats[0].seatId')
seat2=$(echo $availableSeatsResponse | jq -r '.availableSeats[1].seatId')
seat3=$(echo $availableSeatsResponse | jq -r '.availableSeats[2].seatId')


# Set up the request body for the POST request
echo "Setting up the request body for the POST request..."
requestBody=$(cat <<EOF
{
   "screeningId":"$screeningId",
   "seats":[
      "$seat1",
      "$seat2",
      "$seat3"
   ],
   "ticketsType":[
      "CHILD",
      "STUDENT",
      "CHILD"
   ],
   "person":{
      "name":"Jan",
      "surname":"Kowalski-Antoniak"
   }
}
EOF
)

# Make the POST request to book the selected seats
echo "Making the POST request to book the selected seats..."
bookingResponse=$(curl -X POST -H "Content-Type: application/json" -d "$requestBody" "http://localhost:9090/booking/book")

# Print the response
echo $bookingResponse