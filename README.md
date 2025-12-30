# 🎓 University Course Seat Allocation System  
### Microservices-Based Backend System (Spring Boot)

---

## 1. Problem Statement

In a university environment, course seat allocation is a complex and sensitive process.  
Each course has a **limited number of seats**, while the number of student applicants often exceeds availability. The traditional manual or monolithic systems suffer from:

- Lack of transparency in allocation
- Difficulty enforcing merit-based rules
- Poor scalability during peak registration times
- Tight coupling between authentication and business logic

The goal of this project is to design and implement a **scalable, secure, and modular backend system** that can **fairly allocate course seats based on merit**, handle **waitlists**, and ensure **secure access** using modern architectural practices.

---

## 2. System Objectives

The system is designed to achieve the following objectives:

- Authenticate users securely
- Allocate course seats based on student merit (CGPA)
- Enforce course seat limits
- Automatically manage waitlisted students
- Provide transparent and queryable allocation results
- Maintain separation of concerns using microservices

---

## 3. Domain Finalization

### Selected Domain  
**University Course Seat Allocation System**

This domain was chosen because it naturally involves:
- Constrained resources (limited seats)
- Priority-based decision making (CGPA)
- Multiple actors with distinct responsibilities
- High relevance to real-world academic systems

---

## 4. Actors Involved

### 4.1 Student
- Registers and logs into the system
- Applies for courses
- Views seat allocation or waitlist status

### 4.2 Admin (System Authority)
- Configures courses and seat limits
- Triggers seat allocation process
- Monitors overall allocation results

---

## 5. Microservice Decomposition Strategy

The system is decomposed using **Business Capability Decomposition** rather than technical layers.

### Why Business Capability Decomposition?
- Each service owns a clear business responsibility
- Services evolve independently
- Reduces cross-service dependencies
- Aligns with real enterprise system design

---

## 6. Microservices Description

### 6.1 Authentication Service (auth-service)

#### Responsibility
- Manages user identity and access
- Handles registration and login
- Issues authentication tokens

#### Design Rationale
Authentication is a **cross-cutting concern** and should not be coupled with business logic.  
By isolating authentication into its own service:
- Security policies can evolve independently
- Business services remain lightweight and focused

The auth-service **only issues tokens** and does not enforce business-level authorization rules.

---

### 6.2 Seat Allocation Service (seat-allocation-service)

#### Responsibility
- Handles the core business logic of seat allocation
- Enforces merit-based rules
- Manages seat limits and waitlists
- Persists allocation results

#### Design Rationale
This service contains **domain-specific rules**, such as:
- Sorting students by CGPA
- Allocating seats until limits are reached
- Marking remaining students as waitlisted

Keeping this logic isolated ensures:
- Better testability
- Independent scalability
- Clear ownership of academic rules

---

## 7. Communication Mechanisms

### 7.1 REST Communication
- Used for client-facing APIs
- Enables simple and standardized interaction
- Suitable for CRUD-style operations and queries

### 7.2 Token-Based Authentication (JWT)
- Stateless authentication
- Tokens are issued by auth-service
- Tokens are validated by downstream services
- Enables horizontal scaling without session sharing

---

## 8. Seat Allocation Logic (Problem-Oriented View)

The seat allocation process follows a deterministic and fair workflow:

1. Retrieve all students eligible for a given course
2. Sort students in descending order of CGPA
3. Allocate seats up to the predefined seat limit
4. Students beyond the seat limit are automatically waitlisted
5. Allocation results are persisted for transparency and auditing

This ensures:
- Merit-based fairness
- No over-allocation of seats
- Automatic waitlist handling without manual intervention

---

## 9. CQRS (Command Query Responsibility Segregation)

The system applies CQRS to separate **write operations** from **read operations**.

### Commands
- Seat allocation actions that modify system state

### Queries
- Fetching allocation results for students

### Benefits
- Clear separation of responsibilities
- Improved maintainability
- Easier scalability for read-heavy workloads

---

## 10. Database Design Approach

### Database-Per-Microservice Pattern
Each microservice maintains its own database schema.

#### Benefits:
- Loose coupling between services
- Independent schema evolution
- Improved fault isolation

This prevents changes in one service from unintentionally impacting others.

---

## 11. Security Design Considerations

- Authentication is handled centrally by auth-service
- Tokens are stateless and time-bound
- Downstream services validate tokens for protected operations
- Sensitive operations require valid authentication

This design:
- Avoids tight coupling
- Supports future API Gateway integration
- Follows industry security best practices

---

## 12. Non-Functional Considerations

### Scalability
- Stateless services enable horizontal scaling
- Database isolation prevents contention

### Maintainability
- Clear service boundaries
- Layered architecture inside each service

### Extensibility
- Easy to add features such as category-based reservations
- Can integrate messaging systems for notifications

---

## 13. Learning Outcomes

Through this project, the following concepts were practically applied:

- Microservice architecture design
- JWT-based authentication
- Business capability decomposition
- CQRS pattern
- Spring Boot and Spring Security
- Real-world backend system modeling

---

## 14. Conclusion

This project presents a **realistic backend architecture** for solving the university seat allocation problem using modern enterprise development practices.  
It demonstrates how complex academic processes can be transformed into **scalable, secure, and maintainable systems** using microservices and well-defined design patterns.

---
