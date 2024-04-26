# SlackAPI
This repository contains the Slack API Proxy Service, designed to securely interface with the Slack API, limiting public access and reducing potential abuse through rate limiting and secure configuration.

# Configuration

1. Navigate to the src/main/resources/ directory.
2. Open the application.properties file.
3. Locate the line starting with slack.webhook.url=.
4. Replace the existing URL with your Slack webhook URL.

Example:
```
slack.webhook.url=https://hooks.slack.com/services/Your-Webhook
```
# Building the Application
After configuring your application.properties, the next step is to build the application using Maven.
```
mvn clean package
```
# Running the Application
Once the application is built, you can run it directly from the command line
```
java -jar target/slackapi-proxy.jar
```
# Running as a Docker Container
To run the service within a Docker container, use the following command:
```
docker run --name slackapi-container -p 8080:8080 -e MAXREQUEST=3 -e WEBHOOK=https://hooks.slack.com/services/Your-Webhook slackapi-image
```
-e MAXREQUEST=3: Sets an environment variable MAXREQUEST inside the container, which limits the number of requests per minute to the service to prevent abuse.

-e WEBHOOK=https://hooks.slack.com/services/Your-Webhook: Sets an environment variable WEBHOOK with your Slack webhook URL. Replace https://hooks.slack.com/services/Your-Webhook with your actual webhook URL.

# API Documentation

Access the Swagger API documentation at:
```
http://localhost:8080/swagger-ui/index.html
```
