# Student Registration and Management System

A Spring MVC web application developed using Java, Thymeleaf, and PostgreSQL to register and manage student records.

---

## Technical Overview & Database Schema

### Database Configuration
- **Database Name:** `student_management`
- **Table Name:** `students`

```sql
CREATE DATABASE student_management;

\c student_management;

CREATE TABLE students (
    id SERIAL PRIMARY KEY,
    full_name VARCHAR(150) NOT NULL,
    age INTEGER CHECK (age > 18),
    email VARCHAR(150) UNIQUE NOT NULL
);