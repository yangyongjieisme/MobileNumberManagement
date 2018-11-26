#!/bin/sh

echo "start running"
echo "------------"
print_empty(){

	echo ""
	echo "--------------------------------------------"
	echo ""
}



curl -u admin:admin -i -X GET -H "Content-Type: application/json" http://localhost:8080/getAllUsers
print_empty
   
curl -u admin:admin -i -X GET -H "Content-Type: application/json" http://localhost:8080/getAllMobiles
print_empty
      	
curl -u admin:admin -i -X GET -H "Content-Type: application/json" http://localhost:8080/getAllServices
print_empty
      
curl -s -u admin:admin -i -X POST -H "Content-Type: application/json" --data '{"account":"account_1","number":"95555555" }'\
      		http://localhost:8080/bindNumber
      		
print_empty

curl -s -u admin:admin -i -X POST -H "Content-Type: application/json" --data '{"account":"account_1","number":"95555555","service":"Roaming" }'\
      		http://localhost:8080/bindService
      		
print_empty

curl -s -u admin:admin -i -X POST -H "Content-Type: application/json" --data '{"account":"account_1","number":"95555555","service":"Others" }'\
      		http://localhost:8080/bindService
      		
print_empty

curl -s -u admin:admin -i -X POST -H "Content-Type: application/json" --data '{"account":"account_1","number":"96666666"}'\
      		http://localhost:8080/bindNumber
      		
print_empty

curl -s -u admin:admin -i -X POST -H "Content-Type: application/json" --data '{"account":"account_2","number":"97777777" }'\
      		http://localhost:8080/bindNumber
      		
print_empty

curl -u admin:admin -i -X GET -H "Content-Type: application/json" http://localhost:8080/getUserFullPicture?account=account_1
print_empty

curl -u admin:admin -i -X GET -H "Content-Type: application/json" http://localhost:8080/getUserFullPicture?account=account_2
print_empty

curl -s -u admin:admin -i -X POST -H "Content-Type: application/json" --data '{"account":"account_1","number":"95555555","service":"Others" }'\
      		http://localhost:8080/unBindService
      		
print_empty

curl -u admin:admin -i -X GET -H "Content-Type: application/json" http://localhost:8080/getUserFullPicture?account=account_1
print_empty

curl -s -u admin:admin -i -X POST -H "Content-Type: application/json" --data '{"account":"account_1","number":"95555555" }'\
      		http://localhost:8080/unBindNumber
      		
print_empty


curl -u admin:admin -i -X GET -H "Content-Type: application/json" http://localhost:8080/getUserFullPicture?account=account_1
print_empty

curl -u admin:admin -i -X GET -H "Content-Type: application/json" http://localhost:8080/getAvailableMobiles
print_empty

curl -u admin:admin -i -X GET -H "Content-Type: application/json" http://localhost:8080/getCurrentStatus?number=96666666
print_empty

curl -u admin:admin -i -X GET -H "Content-Type: application/json" http://localhost:8080/getMobileHistory?number=95555555
print_empty

echo "------------"
echo "finish running"
exit 0
