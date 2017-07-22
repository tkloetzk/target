# myRetail RESTful service Case Assessment

myRetail is a rapidly growing company with HQ in Richmond, VA and over 200 stores across the east coast. myRetail wants to make its internal data available to any number of client devices, from myRetail.com to native mobile apps. 
The goal for this exercise is to create an end-to-end Proof-of-Concept for a products API, which will aggregate product data from multiple sources and return it as JSON to the caller. 
Your goal is to create a RESTful service that can retrieve product and price details by ID. The URL structure is up to you to define, but try to follow some sort of logical convention.
Build an application that performs the following actions: 
*	Responds to an HTTP GET request at /products/{id} and delivers product data as JSON (where {id} will be a number. 
*	Example product IDs: 15117729, 16483589, 16696652, 16752456, 15643793) 
*	Example response: {"id":13860428,"name":"The Big Lebowski (Blu-ray) (Widescreen)","current_price":{"value": 13.49,"currency_code":"USD"}}
*	Performs an HTTP GET to retrieve the product name from an external API. (For this exercise the data will come from redsky.target.com, but let’s just pretend this is an internal resource hosted by myRetail) 

*	Example: http://redsky.target.com/v2/pdp/tcin/13860428?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics
*	Reads pricing information from a NoSQL data store and combines it with the product id and name from the HTTP request into a single response. 

•	BONUS: Accepts an HTTP PUT request at the same path (/products/{id}), containing a JSON request body similar to the GET response, and updates the product’s price in the data store. 

## Software Stack
* [Java 1.8](https://java.com/en/download/)
* [MongoDB 3.4.6](https://www.mongodb.com/)
* [Spring Boot](https://projects.spring.io/spring-boot/) with [Tomcat](https://tomcat.apache.org/) Embedded
* [JUnit 4](http://junit.org/junit4/)
* [Maven 3.5.0](https://maven.apache.org/)
* [Git 2.13.1](https://git-scm.com/)

## MYRETAIL APP
MyRetail App is configured to run as a Spring Boot application. It is configured to run at http://localhost:8080/products/  

### To start the application
*If you're using a mac, you can easily download everything using [homebrew](https://brew.sh)

To get homebrew, simple past the following command into your terminal
```
/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
```

### 1. Clone the project from Git.
If you do not have git installed, [click here.](https://git-scm.com/downloads) If you're unsure, open your terminal/command prompt and type in 
```
git --version
```

If on a mac using homebrew, you can type the following command to install git
```
brew install git
```

Once git is installed, clone the project from git

In terminal/command prompt, run-
```
$ git clone https://github.com/tkloetzk/target
```

### 2. Build using Maven

If you do not have maven installed, you can install it [here.](https://maven.apache.org/download.cgi)

If you're unsure, enter this command in your terminal/command prompt
```
mvn -version
```

If on a mac using homebrew, you can type the following command to install maven
```
brew install maven
```


If you don't have mongoDB installed, refer here: https://docs.mongodb.com/manual/tutorial/install-mongodb-on-os-x/

Open your terminal/command prompt and navigate to root level of the project (same level as the pom.xml)
example: cd /folder/projectFolder

Then run maven clean install
**Build:**
mvn clean install

To run the server, move into the target folder
example: cd /target
And run the jar
**Run the server:**
java -jar myretail-0.0.1-SNAPSHOT.jar

I have used MongoDB as my NoSQL database to the product's information. The collection is named Product

# Mongo Query to insert documents:
db.getCollection('Product').save({ "pid": 13860428, "title": "The Big Lebowski (Blu-ray)", "price": "69.87", "currencyCode": "USD"  })

**Myretail hosts two REST services:**

**1) GET /products/{productId}**
   
  This Rest Service aggregates price information from MongoDB and product Title from external Target API and
  provides a JSON Response.
  
  **Sample:**
  
  **Request:** 
  GET   http://localhost:8080/myretail/products/13860428
  Content-Type: application/json
  
  **Response:**
  {
    "id": 13860428,
    "name": "The Big Lebowski (Blu-ray)",
    "current_price": {
        "value": "100.0",
        "currency_code": "USD"
    }
}
  
**2) PUT /products/{productid}**
  
  This Rest Service is used to update the price of an existing product in MongoDB

  **Sample:**
  PUT http://localhost:8080/myretail/products/13860428
  Content-Type: application/json
  
  **Request:**
   { "id": 13860428, "name": "The Big Lebowski (Blu-ray)", "current_price": { "value": "100.00", "currency_code": "USD" } }
   
   **Response:**
   200 OK
