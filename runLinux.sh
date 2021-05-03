#!/bin/bash
java -jar consultant-microservice/target/consultancy-service-1.0-SNAPSHOT.jar &
java -jar client-app/target/ui-1.0-SNAPSHOT-jar-with-dependencies.jar
