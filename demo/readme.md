# BookStore API
## Requirements
- Java 17
- git CLI (download from [here](https://git-scm.com/downloads))
- sqlite 3 (download from [here](https://www.sqlite.org/download.html) (modern operating systems already have sqlite installed))
- Postman

## How to run
1. Clone the repository
```shell
git clone https://github.com/CoderSleek/bookstore.git
cd demo
```
2. Run the following command
```shell
.mvnw clean install -DskipTests
.mvnw spring-boot:run
```
3. The App will run on port 8080 [link](http://localhost:8080/), already set on postman so you don't have to do anything

4. Each request requires a login, you can use the following credentials to login

regular user
```
username: user
password: userpass
```
OR

privileged user
```
username: admin
password: adminpass
```
the values can be edited in the `application.yaml` file

### API endpoints are attached as a `postman.json` collection
- the file can be imported in postman

### API documentation
#### Endpoints
##### Books
- Get All Books  
- URL: /api/v1/books
- Method: GET
- Auth: Basic (username: user, password: userpass)
- Response: 200 OK with a JSON array of books.


- Get Books by Title  
- URL: /api/v1/books/title/{title}
- Method: GET
- Auth: Basic (username: user, password: userpass)
- Response: 200 OK with a JSON array of books.


- Get Books by Author  
- URL: /api/v1/books/author/{authorName}
- Method: GET
- Auth: Basic (username: user, password: userpass)
- Response: 200 OK with a JSON array of books.


- Add a Book  
- URL: /api/v1/books
- Method: POST
- Auth: Basic (username: user, password: userpass)
- Request Body: JSON object representing the book.
- Response: 201 Created with the created book.


- Update a Book  
- URL: /api/v1/books/{isbn}
- Method: PUT
- Auth: Basic (username: user, password: userpass)
- Request Body: JSON object representing the updated book.
- Response: 200 OK with the updated book.


- Delete a Book  
- URL: /api/v1/books/{isbn}
- Method: DELETE
- Auth: Basic (username: admin, password: adminpass)
- Response: 204 No Content

##### Authors
- Create an Author  
- URL: /api/v1/authors
- Method: POST
- Auth: Basic (username: admin, password: adminpass)
- Request Body: JSON object representing the author.
- Response: 201 Created with the created author.


- Get Authors
- URL: /api/v1/authors
- Method: GET
- Auth: Basic (username: admin, password: adminpass)
- Response: 200 OK with a JSON array of authors.