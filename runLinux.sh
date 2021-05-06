#!/bin/bash
java -jar consultant-microservice/target/consultancy-service-1.0-SNAPSHOT.jar &
java -jar accountancy-microservice/target/accountancy-microservice-1.0-SNAPSHOT.jar &
java -jar client-app/target/client-app-1.0-SNAPSHOT-jar-with-dependencies.jar
