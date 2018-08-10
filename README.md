# Coffee Shop REST Service

Uses Spring Boot and Spring Security for secure REST webservices
[JWT](https://jwt.io/introduction/) token.

##### 1. Technology Used

- Java 1.8
- Spring Boot (Web, JPA, Security)
- Mysql 5.6
- Maven 3
- Swagger 2

#### Run and Verify

##### 1. Compile and package
```bash
mvn clean package
```
Make sure you have installed all java 1.8 , Mysql >= 5.6 and Maven 3

##### 2. Start services
```bash
java -jar coffee-shop-service/target/coffeeshop-services-0.0.1-SNAPSHOT.jar
```
Make sure database connection settings are proper, please have a look at
``coffee-shop-service/src/main/resource/application-development.properties``
##### 3. Get swagger ui documentation in browser
```bash
http://localhost:8008/swagger-ui.html
```
port number depends on ``coffee-shop-service/src/main/resource/application-development.properties``
property ``server.port``.
You will see the REST API swagger 2 documentation and can now use any REST API.
For REST API version handling used custom ``COFFEESHOP-API-VERSION``.
