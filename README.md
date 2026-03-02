# 📝 Blog Platform API – Spring Boot + JWT + PostgreSQL

A full-stack Blog Platform backend built using **Java, Spring Boot, Spring Security, and PostgreSQL**.

This project demonstrates real-world backend architecture including authentication, authorization, entity relationships, REST API design, and JWT-based security.

---

## 🚀 Tech Stack

- Java 17+
- Spring Boot
- Spring Security
- JWT Authentication
- PostgreSQL
- Spring Data JPA (Hibernate)
- MapStruct
- Maven
- REST APIs

---

## 📌 Features

### 🔐 Authentication & Security
- User registration & login
- JWT-based authentication
- Role-based authorization
- Secure endpoints

### 🗂 Blog Management
- Create, update, delete posts
- Draft & published posts
- Category management
- Tag management
- User-Post relationship
- Category-Post relationship
- Tag-Post relationship

### ⚙️ Backend Architecture
- Clean layered architecture
- Controller → Service → Repository pattern
- DTO mapping using MapStruct
- Global exception handling
- Proper REST standards

---

## 🏗 Project Architecture
src
- └── main
- └── java
- └── com.blog
- ├── config
- ├── controller
- ├── service
- ├── repository
- ├── entity
- ├── dto
- ├── mapper
- └── exception

  
---

## 🗄 Database Setup (PostgreSQL)

### 1️⃣ Run PostgreSQL

Make sure PostgreSQL is running locally.

Create database:

```sql
CREATE DATABASE blog_db;

- spring.datasource.url=jdbc:postgresql://localhost:5432/blog_db
- spring.datasource.username=your_username
- spring.datasource.password=your_password

- spring.jpa.hibernate.ddl-auto=update
- spring.jpa.show-sql=true
- spring.jpa.properties.hibernate.format_sql=true


# ▶️ Running the Application

### Clone the repository

```bash
git clone https://github.com/your-username/blog-platform.git
cd blog-platform

| Method | Endpoint               | Description       |
| ------ | ---------------------- | ----------------- |
| POST   | /api/auth/register     | Register new user |
| POST   | /api/auth/authenticate | Login user        |


```

---
# 📚 What I Learned

- Designing RESTful APIs

- Implementing JWT authentication

- Role-based authorization

- Entity relationships in JPA

- Exception handling best practices

- Clean backend architecture
---
# 📌 Future Improvements

- Pagination & Sorting

- Comment system

- Image upload

- Refresh token mechanism

- Docker support

- Deployment to cloud (AWS / Render)
---
<img width="1594" height="892" alt="image" src="https://github.com/user-attachments/assets/395b2ed8-01ba-43f6-a41a-edd63e146952" />

# 👨‍💻 Author

# Anant Tiwary
Backend Developer | Spring Boot Enthusiast
Bhubaneswar, India
