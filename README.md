# Friend Management Application
This is an application that build its own social network, "Friends Management" is a common requirement which usually starts off simple but can grow in complexity depending on the application's use case. Usually, the application comprised of features like "Friend", "Unfriend", "Block", "Receive Updates" etc.
# Technology Used
# Spring Boot
1.	Spring Boot allows easy setup of for startup applications.
2.	Its a best solution for implementing domain driven design concepts.
3.	In memory Database H2 is used here  (http://localhost:8086/friendmgt/h2/)
# JDBC Template
1.	Spring JdbcTemplate is a powerful mechanism to connect to the database and execute SQL queries.
2.	It internally uses JDBC api, but eliminates a lot of problems of JDBC API. H2 Database (In memory database)
3.	H2 is an open-source lightweight Java database.
4.	H2 database can be configured to run as in-memory database, which means that data will not persist on the disk.
# Swagger

1.	 Swagger is used for visualizing APIs, and with Swagger UI it provides online sandbox for frontend developers
2.	Swagger is a framework for describing API using a common language that everyone can understand.
3.	 The Swagger spec standardizes API practices, how to define parameters, paths, responses, models, etc.

# JavaDocs:
Used Javadoc tool which comes with JDK for generating Java code documentation in HTML format from Java source code
# PCF
1.	Pivotal Cloud Foundry is an open-source platform as a service (PaaS), which provides platform to deploy microservices with minimum effort. 
2.	Pivotal Cloud Foundry is designed to be configured, deployed, managed the services.
3.	Pivotal Cloud Foundry helps us to scale up or scale down the application which increase the system performance

# Deployment to PCF:

The Application is deployed on Pivotal cloud and can be accessed via the URL {pcf generated url}/api .  i.e https://spstestapi.cfapps.io/friendmgt/api

For example: To access /create endpoint, the URL should be: https://spstestapi.cfapps.io/friendmgt/api/createfriendship

Swagger UI is configured for the app and it is available https://spstestapi.cfapps.io/friendmgt/swagger-ui.html#/friend-mgt-controller
---- http://localhost:8086/friendmgt/swagger-ui.html


# List of REST Endpoints 
# 1. Establish Friendship between two persons
	a. URI:  https://spstestapi.cfapps.io/friendmgt/api/createfriendship
	b. Method: POST
	c. Header: Content Type – Application/Json
	d. Request body: 
	{
	    "requestor":"raj@example.com",
          "target":"rohit@example.com"	  
         }
	e. Response body
	   	{
	      "status": "Success",
	      "description": "Successfully connected"
	   }

     Defined Errors:
      •	200: Success.
      •	400: Bad Request
      
      
# 2. Returns a list of friends of a person.
   a.	 URI:  https://spstestapi.cfapps.io/friendmgt/api/friendlist
   b.	Method: POST
   c.	Header: Content Type – Application/Json
   d.	Request body: 
   {
      "email":"raj@example.com"
   }
   e.	Response body
       {
           "status": "Success",
           "count": 1,
           "friends": [
           "rohit@example.com"
           ]
       }


     Defined Errors:
      •	200: Success.
      •	400: Bad Request
      
      
      
# 3. Returns list of common friends of two persons
   a.	 URI:  https://spstestapi.cfapps.io/friendmgt/api/friends
   b.	Method: POST
   c.	Header: Content Type – Application/Json
   d.	Request body: 
        {
            "friends": ["raj@example.com",
    
              "rahul@example.com"
          ]
   
        }
   e.	Response body
              {
             "status": "Success",
               "count": 1,
               "friends": [
               "rohit@example.com"
           ]              }

     Defined Errors:
      •	200: Success.
      •	400: Bad Request
      
      
# 4. Person subscribe to another Person
   a.	URI:  https://spstestapi.cfapps.io/friendmgt/api/subscribe
   b.	Method: POST
   c.	 Header: Content Type – Application/Json
   d.	 Request body: 
        {
            "requestor":"rohit@example.com",
            "target":"rahul@example.com"
   }
   e.	 Response body
      {
              "status": "Success",
              "description": "Subscribed successfully" 
       }

     Defined Errors:
      •	200: Success.
      •	400: Bad Request
      
      
# 5. Person block updates from another Person
   a.	   URI:  https://spstestapi.cfapps.io/friendmgt/api/unsubscribe
   b.	Method: POST
   c.	 Header: Content Type – Application/Json
   d.	Request body: 
        {
            "requestor":"rohit@example.com",
            "target":"rahul@example.com"

     }
   e.	 Response body
       {
            "status": "Success",
              "description": "Unsubscribed successfully"
        }

    Defined Errors:
      •	200: Success.
      •	400: Bad Request
      
      
      
# 6. Post an update which returns a list of emails that will receive the update.
   a.	 URI:  https://spstestapi.cfapps.io/friendmgt/api/friends/updatelist
   b.	Method: POST 
   c.	Header: Content Type – Application/Json 
   d.	Request body: 
        {
           "sender":"raj@example.com",
	  "text": " Hi! ranga@gmail.com"      
        }
   e.	 Response body
       {
            "status": "Success",
          "friends": [
          "rohit@example.com",
          "rahul@example.com",
          "roopesh@example.com",
          "ranga@gmail.com"
          ]      
     }

     Defined Errors:
      •	200: Success.
      •	400: Bad Request




# 7. Database:
I have used in memory database (H2) for friend management api's development. Here i have used pre populated data for 4 persons for testing purpose, the data is loaded from SQL script file which is placed inside the code repository.
Below are table snap shots for this development task:

	## A. Table Name: USER_MANAGMNT user_managmnt
	
	id | int | PK
	EMAIL | Varchar | Null
	FRIEND_LIST | VARCHAR | Null
	SUBSCRIBER | VARCHAR |  Null
	SUBSCRIBEDBY	 | VARCHAR | Null
	UPDATED | VARCHAR | Null
	UPDATED_TIMESTAMP | DATE | Not Null
	
	## B.Table Name: UNSUBSCRIBE
	
	ID | INT | PK
	REQUESTOR_EMAIL | 	VARCHAR | Null
	TARGET_EMAIL | VARCHAR | Null
	SUBSCRIPTION_STATUS | VARCHAR | Null
