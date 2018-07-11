# Credit Suisse Technical Test

##Description
This Spring Boot Command Line project takes one parameter, path to a text file which contains log and application log entries in **valid** JSON format. 
When project is run, it populates HSQLDB database, named `event`, under `db` directory.

## Requirements
* Java 8

## Running tests
```
cd PROJECT_DIR
./gradlew clean assemble test
``` 

## Running project
* Unix
```
cd PROJECT_DIR
./gradlew clean build
java -jar build/libs/demo-0.0.1-SNAPSHOT.jar path/to/input/file
``` 
