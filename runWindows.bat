@echo off
start "Title 1" java -jar consultant-microservice/target/consultancy-service-1.0-SNAPSHOT.jar &
start "Title 2" java -jar client-app/target/ui-1.0-SNAPSHOT-jar-with-dependencies.jar
pause
