# Finance Backend System

This project is a backend system built using Spring Boot for managing financial records and users with role-based access. The goal of this project is to demonstrate clean backend architecture, proper API design, and data processing logic for a finance dashboard system.

The application allows users to create and manage financial records, apply filters, and view aggregated insights such as total income, expenses, and category-wise breakdown. The system is designed to be simple, maintainable, and easy to extend.

---

## Live API

https://finance-backend-xoiq.onrender.com

Note: This is a backend-only application. The root URL may not display a UI. APIs can be accessed using tools like Postman, Thunder Client, or directly via endpoints.

---

## Tech Stack

- Java with Spring Boot  
- Spring Data JPA  
- H2 in-memory database  
- Spring Security (for role-based access control)  
- Maven  
- Docker (for deployment)  

---

## Project Overview

The backend simulates a finance dashboard system where different users interact with financial data based on their roles. It supports CRUD operations for financial records, flexible filtering, pagination, and dashboard-level analytics.

The project follows a layered architecture to maintain clean separation of concerns and scalability.

---

## Project Structure

The code is organized into the following layers:

- controller → Handles API requests and responses  
- service → Contains business logic  
- repository → Handles database interaction  
- model → Entity classes  
- dto → Response structures  
- exception → Global error handling  
- config → Security configuration  

This structure ensures the project remains clean and maintainable.

---

## User and Role Management

The system supports three roles:

- Viewer → Can view records only  
- Analyst → Can view records and dashboard insights  
- Admin → Full access (create, update, delete users and records)  

Features implemented:

- Creating users  
- Assigning roles  
- Managing user status (active/inactive)  
- Role-based access control  

---

## Role-Based Access Control (RBAC)

Role-based access is implemented using Spring Security with basic authentication.

Each role has restricted access:

- Viewer → Read-only access to records  
- Analyst → Read access + dashboard insights  
- Admin → Full CRUD access  

Endpoints are protected using method-level security annotations such as `@PreAuthorize`.

---

## Financial Records Management

Each financial record contains:

- Amount  
- Type (INCOME or EXPENSE)  
- Category  
- Date  
- Notes  

Supported operations:

- Create record  
- View records  
- Update record  
- Delete record  

---

## Filtering and Pagination

The backend supports flexible querying and pagination:

### Combined Filtering
- `/records/search?type=INCOME&category=Food`
- `/records/search?start=2026-04-01&end=2026-04-30`

### Pagination and Sorting
- `/records?page=0&size=10&sortBy=date`

This approach reflects real-world API design where multiple filters can be applied together.

---

## Dashboard APIs

The system provides aggregated insights:

- Total income  
- Total expenses  
- Net balance  
- Recent transactions (latest 5 records)  

Endpoint:

GET `/dashboard`

Aggregation logic is implemented in the service layer.

---

## API Endpoints

User APIs:

- POST `/users` → Create user  
- GET `/users` → Retrieve all users  

Financial Record APIs:

- POST `/records` → Add record  
- GET `/records` → Get paginated records  
- PUT `/records/{id}` → Update record  
- DELETE `/records/{id}` → Delete record  
- GET `/records/search` → Filter records  

Dashboard API:

- GET `/dashboard` → Get summary data  

---

## Sample Request

Example of creating a financial record:

{
  "amount": 5000,
  "type": "INCOME",
  "category": "Salary",
  "date": "2026-04-03",
  "notes": "Monthly salary"
}

---

## Setup Instructions

To run the project locally:

1. Clone the repository  
2. Navigate to the project directory  
3. Run:

mvn clean install  
mvn spring-boot:run  

The application will start on:
http://localhost:8080

---

## Testing the APIs

You can test the APIs using:

- Postman  
- Thunder Client (VS Code)  
- Browser (for GET endpoints)  

Example endpoints:

- http://localhost:8080/records  
- http://localhost:8080/dashboard  

Authentication is required. Use the following test credentials:

- Admin → admin / admin123  
- Analyst → analyst / analyst123  
- Viewer → viewer / viewer123  

---

## Validation and Error Handling

The system includes:

- Input validation using annotations (`@NotNull`, `@Positive`, `@NotBlank`)  
- Request validation using `@Valid`  
- Proper HTTP status codes  
- Global exception handling for consistent responses  

---

## Database

- Uses H2 in-memory database  
- No external setup required  
- Suitable for testing and demonstration  

---

## Assumptions Made

- Basic authentication is used instead of full JWT-based authentication  
- H2 database is used for simplicity and easy deployment  
- The system is designed for demonstration and evaluation purposes  

---

## Trade-offs Considered

- Used basic authentication instead of full security implementation to keep focus on core backend logic  
- Used H2 instead of a production database like MySQL for simplicity  
- Frontend was not implemented since the assignment focuses on backend  

---

## Notes

This project demonstrates:

- Clean backend architecture  
- REST API design  
- Data processing and aggregation  
- Pagination and filtering  
- Role-based access control  

The system can be extended with JWT authentication, persistent databases, advanced analytics, and frontend integration if required.
