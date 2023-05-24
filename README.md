# Course Registration System

## Directory Structure:
 - CRS-Flipkart-GroupF-Documentation: This folder contains the documentation for our Course registration application which is generated using JavaDocs.
 - CRS-Flipkart-GroupF-POS: This contains the source code for the application. It contains the following major layers:
  - Beans: The re-usable units of the application
  - Service: This layer processes the user input and performs the required function.
  - DAO: This layer connects to the database and performs the CRUD operations.
  - Client: This contains the entry point of the application. This layer takes input from the user and passes it on to the service layer
 - CRS-Flipkart-GroupF-REST-DropWizard: This folder contains the API endpoints for our application.
 - CRS-Flipkart-GroupF-Sprint-Goal-UML: This folder contains the problem statement, UML diagram, UseCase diagram, etc for our application.

## Requirements and dependencies:
1. Java: Version 11
2. MySQL: Version 8.0.24
3. Apache Maven: Version 3.9.2

## Initial Set up:
Add your mysql credentials in the `src/config.properties` file in the required directory.

## Running the Application:
### Using Command Line:
1. Open the CRS-Flipkart-GroupF-POS directory in an IDE.
2. Run the CRSApplication using the IDE.

### Using the API:
1. Navigate into the CRS-Flipkart-GroupF-REST-DropWizard directory in your terminal.
2. Create a jar file of the application using the maven command: ` $ mvn clean package `
3. Start the server by running the created jar file using the command: ` $ java -jar target/CRS-Application-1.0-SNAPSHOT.jar server `
