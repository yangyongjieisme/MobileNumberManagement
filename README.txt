How to deploy and run the program 

1. Download projects from Github

2. For 

b) in console, go to YOUR_UNZIPPED_DIR, run the following command
    mvn spring-boot:run
   to start to server, once server statred the url is http://localhost:8080 
c) go into YOUR_UNZIPPED_DIR/script, you will see 
     runRequest.sh  -- a bash script to run the http requests
     input.csv   -- the input data (You can change the data as you want)
   After running the script, result.txt will be generated containing the running output
   
d) Alternatively, yu can use curl or run in browsers. Following is the endpoints with example data:

  
 