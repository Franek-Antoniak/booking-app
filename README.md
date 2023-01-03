To use the touk-ticket-booking application, follow these steps:

1. Make sure you have Docker installed on your Linux system. If not, visit the following link to install Docker on
   Ubuntu: https://docs.docker.com/engine/install/ubuntu/
2. Clone the touk-ticket-booking repository and navigate to the root directory.
3. Run the docker-script.sh file with the command bash docker-script.sh. This will build the touk-ticket-booking
   image and start the containers.
4. Run the demo-script.sh file with the command bash demo-script.sh. This script will demonstrate the use cases of the
   touk-ticket-booking application.

Note: The demo-script.sh file makes HTTP requests to the touk-ticket-booking application, so make sure the containers
are running before running this script.