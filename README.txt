How to deploy and run the program 

1. Download projects from Github
  
  a) https://github.com/yangyongjieisme/SpringCloudEurekaNamingServer.git
   	 to folder, eg : /eureka_server
  b) https://github.com/yangyongjieisme/MobileNumberManagement.git
  	 to folder, eg: number_management 

2. in  /eureka_server , run 
	mvn spring-boot:run
   this	will create the registry server
   in /number_management, run
   mvn spring-boot:run
   this will start the service server.
   
   After all the servers are up, go to http://localhost:8761/, you should see the service server is registered
   

3. in /number_management/script
	run the shell script, ./runRequest.sh
   you should be able to see sample out	  
 
 
 4. You can edit /number_management/src/main/resources/schema.sql to change the initial loaded data ,
    and edit /number_management/script/runRequest.sh for requests.