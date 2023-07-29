# Top Shelf Books

## Overview
This project is an online e-commerce book store website developed as part of a case study for a Java Spring Boot course. The application allows users to browse and purchase books online, manage their accounts, and interact with the bookstore's inventory.

### Key Features
- User Registration and Login: Users can sign up for an account and securely log in using bcrypt-hashed passwords.
- Book Catalog: Browse a wide selection of books available in the online store.
- Shopping Cart: Add books to the shopping cart and proceed to checkout for purchase.
- User Dashboard: Manage account details, order history, and payment information.
- Search and Filter: Easily find books through search and filter options.
- Responsive Design: The website is optimized for various screen sizes and devices.
- Secure Transactions: Ensure secure payment processing and data handling.

## Project Structure and Standardization
The project follows standard Java naming conventions and package structure:

## Core Java and Models
The application utilizes core Java classes with constant variables for various purposes. It includes four models, each corresponding to a table in the relational database. The models are optimized and implement exception handling for robustness.

## Database, ORM, and Hibernate
MySQL is used as the DBMS for the application. A schema diagram and the SQL scripts used for the database setup are included. The database configuration file is correctly set up in the Spring application using "application.properties". Custom queries and all four CRUD operations are implemented through Hibernate.

## Front-end Development
CSS is used to style the web pages, with an external stylesheet for customization. The application includes six different views/pages, dynamically generated using Thymeleaf. External JavaScript files are used to enhance interactivity, and a navigation section is included across multiple pages.

## Spring Framework
The project is built using Spring Boot, with models annotated for binding using Spring Data and Hibernate validation. It includes repositories and service classes/interfaces for data management. Dependency injection is correctly implemented using the @Autowired annotation. The application demonstrates session management and utilizes transaction and request/response logging.

## Web Services
The project incorporates RESTful web services using Spring REST, allowing smooth communication between the front-end and back-end components.

## Unit Testing
Unit testing is thoroughly carried out to ensure the reliability of the application. Each query method in repositories and at least one method in each service class are tested, including parameterized tests.





---
Please note that this is a template, and you should replace "YourUsername" and "YourRepository" in the GitHub link with your actual GitHub username and repository name. Additionally, make sure to provide more detailed information and descriptions of your project's actual implementation, technologies used, and any other relevant details. The above template is meant to serve as a starting point for creating your impressive README file.

