# Consultancy Service Application

## System Overview:
- Microservices are individual spring boot applications.
- Folders 
	1. consultants-microservice (micro service for managing consultants) Connected on localhost port 9003
	1. accountancy-microservice (micro service for managing accountants)
	1. appointment-microservice (micro service for managing appointments)
	1. client-app (a Java Swing Application that connects with these microservices using Apache Http Client Technology)
	
- Run client-app along all micro-services at once on linux by the following command (make sure that you are in project directory)
```
bash runLinux.sh
```
- On windows run runWindows.bat
