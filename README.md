# Blog API Project

## Overview
This Blog API is a RESTful web service designed to manage blog publications. It allows clients to create, read, update, and delete (CRUD) publications through HTTP requests. Built with Spring Boot, it leverages Spring Web, JPA, and Lombok to provide a clean, maintainable codebase.

## Features
- **CRUD Operations:** Supports full CRUD capabilities for blog publications.
- **RESTful Design:** Follows REST principles for intuitive API endpoints.
- **Spring Boot:** Utilizes Spring Boot for rapid development and deployment.
- **JPA Integration:** Uses Spring Data JPA for database interactions, simplifying data access and manipulation.
- **Lombok:** Employs Lombok to reduce boilerplate code for model entities.

## Getting Started
1. **Clone the repository:** `git clone [repository URL]`
2. **Navigate to the project directory:** `cd [project directory]`
3. **Build the project:** `./mvnw clean install`
4. **Run the application:** `./mvnw spring-boot:run`

## API Endpoints
- `POST /api/post` - Create a new publication.
- `GET /api/get` - Retrieve all publications.
- `PUT /api/put/{id}` - Update an existing publication.
- `DELETE /api/delete/{id}` - Delete a publication.

## Technologies Used
- Spring Boot
- Spring Web MVC
- Spring Data JPA
- Lombok
- Maven

## Contributing
Contributions are welcome! Please feel free to submit a pull request.


