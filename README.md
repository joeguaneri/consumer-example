#Producer Application

This application will save a file to a S3 bucket and then send a message to SQS.

##How To Run

###Environment Variables
The following environment variable can be set with this application:

| Property | Values |
|----------|--------|
| DEVTOOLS_ENABLE | true &#124; false |
| S3_BUCKET_NAME | Name of bucket to store files |
| SQS_QUEUE_URL | Queue URL to send events to |

If the application is run locally the following variables are also available:

| Property | Values |
|----------|--------|
| AWS_REGION | Any AWS region, if none provided defaults to **us-east-1** |

###Building the Application
To build the application run the following: ```mvn clean install```

###Running the Application

####Running from Maven
To run the application locally using Maven: ```mvn -Dspring.profiles.active=local spring-boot:run```
