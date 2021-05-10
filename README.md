# Consultancy Service Application

## System Overview:
- Microservices are individual spring boot applications.
- Folders 
	1. consultants-microservice (micro service for managing consultants) Connected on localhost port 9003
	1. accountancy-microservice (micro service for managing accountants)
	1. appointment-microservice (micro service for managing appointments)
	1. client-app (a Java Swing Application that connects with these microservices using Apache Http Client Technology)
	
- Run client-app along all micro-services at once on linux by the following command (make sure that you are in project directory)
``` bash
bash runLinux.sh
```
- On windows run
```
runWindows.bat
```

# The client app includes a fully functional GUI for ease of use communicating to each micro service via HttpClient on local ports 9001 9002 9003, so before starting the application please make sure that you do not have any app running on these ports.

