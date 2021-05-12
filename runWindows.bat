@echo off
start "Title 1" java -jar consultant-microservice/target/consultancy-service-1.0-SNAPSHOT.jar &
start "Title 2" java -jar client-app/target/client-app-1.0-SNAPSHOT-jar-with-dependencies.jar &
start "Title 3" java -jar appointments-microservice/target/appointments-microservice-1.0-SNAPSHOT.jar &
start "Title 4" accountancy-microservice/target/accountancy-microservice-1.0-SNAPSHOT.jar &
pause
