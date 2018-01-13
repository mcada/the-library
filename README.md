# Project description
This is an information system for a public library. The system should keep track of book collections in library departments, library members as well as individual loaned items of every member. The system should be capable of providing information about all the members and books, what a member borrowed and when, who borrowed a certain book and what condition they returned the book in. Take into account that a person can borrow multiple books in a single loan.

# Modules
-----
The project contains the following modules:
* <b>library-persistance</b> - contains entities with service and dao implementations.

# How to run the project
----
Make sure your database is running, you can set the login data in the file
```
library-persistance/src/main/resources/META-INF/persistence.xml
```
Then you can build the project simply by executing these commands from the directory where you cloned the project
```
cd the-library
mvn clean
mvn install
cd web
mvn tomcat7:run
http://localhost:8080/pa165/
```
<b> Login information to dummy accounts <b>
  -----
With those credentials you can log in to the created accounts.
```
test1@test.com - password
admin@admin.com - password
```
#  REST
  ```
mvn clean
mvn install
cd rest
mvn tomcat7:run
  ```

## Books
http://localhost:8080/pa165/rest/books/


### Get all
Method type: GET

example:
curl http://localhost:8080/pa165/rest/books/


### Get book by ID
Method type: GET
http://localhost:8080/pa165/rest/books/{id}
where id is id of book you want to get.

example:
curl http://localhost:8080/pa165/rest/books/1


### Create book
Method type: POST
example:
curl -H "Content-Type: application/json" -X POST -d '{"author":"New Author","title":"Book Title"}' http://localhost:8080/pa165/rest/books


### Update book
Method type: PUT
example:
curl -H "Content-Type: application/json" -X PUT -d '{"author":"New Author","title":"Updated Title"}' http://localhost:8080/pa165/rest/books/2

### Delete
Method type: DELETE
http://localhost:8080/pa165/rest/books/{id}
where id is id of book you want to delete.

example:
curl -X "DELETE" http://localhost:8080/pa165/rest/books/2
