# MyCodeBase

#Errors with solution when connecting from Github to to EC2 using GitActions:
https://stackoverflow.com/questions/71850567/github-actions-workflow-error-ssh-handshake-failed-ssh-unable-to-authenticat

#Code Base
https://medium.com/javarevisited/spring-boot-jpa-crud-with-example-bbd219b5d4a6

# Run the application in post man

POST:
http://ec2-65-0-131-239.ap-south-1.compute.amazonaws.com:8080/user

{
    "userName":"BOB",
    "userAddress":"UT"
}

{
    "userName":"KAT",
    "userAddress":"CO"
}


GET:
http://localhost:8080/users

Delete:
http://localhost:8080/user?userId=1

PUT:
http://localhost:8080/user
{
    "userName":"BOB",
    "userAddress":"NJ"
}