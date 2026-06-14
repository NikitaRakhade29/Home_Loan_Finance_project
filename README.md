# 💰 Home Loan Finance Project

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![REST API](https://img.shields.io/badge/REST_API-FF6C37?style=for-the-badge&logo=postman&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)

---

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Database Schema](#database-schema)
- [Author](#author)

---

## 📌 Overview

**Home Loan Finance** is a backend web application built with **Java Spring Boot** that manages the complete home loan lifecycle — from application submission to final disbursement.

The system allows:
- 🏦 Customers to apply for home loans
- 🔐 Role-based access for Admin, Manager, and Customer
- ✅ Automated workflow: Application → Verification → Sanction → Disbursement
- 📊 Dashboard to track loan status at every stage

> 👩‍🎓 *This project was built as part of my learning journey in Java & Spring Boot.*

---

## ✨ Features

- 🔐 **Role-Based Access Control** — Admin, Manager, and Customer roles
- 📝 **Loan Application** — Customers can submit and track loan applications
- ✅ **Verification Workflow** — Manager verifies submitted documents
- 💸 **Sanction & Disbursement** — Admin approves and disburses loans
- 🗄️ **MySQL Database** — Persistent storage of all loan and user data
- 🌐 **REST APIs** — Clean API endpoints for all operations
- 🔒 **Secure Login** — Authentication for each role

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|-----------|
| **Language** | Java |
| **Framework** | Spring Boot |
| **Database** | MySQL |
| **API Type** | REST API |
| **Build Tool** | Maven |
| **Testing** | Postman |
| **Version Control** | Git & GitHub |

---

## 📁 Project Structure

```
Home_Loan_Finance_project/
│
└── Home_Loan_Project/
    ├── src/
    │   └── main/
    │       ├── java/
    │       │   └── com/homeloan/
    │       │       ├── controller/       # REST API Controllers
    │       │       ├── service/          # Business Logic
    │       │       ├── repository/       # Database Access Layer
    │       │       ├── model/            # Entity Classes
    │       │       └── config/           # Security & App Config
    │       └── resources/
    │           └── application.properties
    └── pom.xml
```

---

## 🚀 Getting Started

### Prerequisites

Make sure you have the following installed:

- Java JDK 17+
- Maven
- MySQL 8.x
- Postman (for API testing)
- IDE — IntelliJ IDEA or Eclipse

### Setup Steps

#### 1. Clone the repository

```bash
git clone https://github.com/NikitaRakhade29/Home_Loan_Finance_project.git
cd Home_Loan_Finance_project/Home_Loan_Project
```

#### 2. Create the MySQL database

```sql
CREATE DATABASE home_loan_db;
```

#### 3. Configure `application.properties`

Open `src/main/resources/application.properties` and update:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/home_loan_db
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

#### 4. Run the application

```bash
mvn spring-boot:run
```

The application will start at:
```
http://localhost:8080
```

---

## 🌐 API Endpoints

### Auth
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/register` | Register new user |
| POST | `/api/auth/login` | Login and get token |

### Loan Application (Customer)
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/loan/apply` | Submit a loan application |
| GET | `/api/loan/status/{id}` | Check loan status |
| GET | `/api/loan/my-loans` | View all my applications |

### Manager
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/manager/pending` | View pending applications |
| PUT | `/api/manager/verify/{id}` | Verify a loan application |

### Admin
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/admin/all-loans` | View all loans |
| PUT | `/api/admin/sanction/{id}` | Sanction a loan |
| PUT | `/api/admin/disburse/{id}` | Disburse a loan |

---

## 🗄️ Database Schema

```
users
├── id (PK)
├── name
├── email
├── password
└── role (ADMIN / MANAGER / CUSTOMER)

loan_applications
├── id (PK)
├── user_id (FK → users)
├── loan_amount
├── property_value
├── status (PENDING / VERIFIED / SANCTIONED / DISBURSED / REJECTED)
├── applied_date
└── updated_date
```

---

## 🔄 Loan Workflow

```
Customer applies
      │
      ▼
  PENDING  →  Manager reviews
      │
      ▼
  VERIFIED  →  Admin sanctions
      │
      ▼
  SANCTIONED  →  Admin disburses
      │
      ▼
  DISBURSED ✅
```

---

## 👩‍💻 Author

**Nikita Rakhade**
- 🎓 B.Tech Computer Engineering — Bajaj Institute of Technology (2025)
- 📊 PG Certificate in Big Data Analytics — CDAC Mumbai
- 📧 nikitarakhade@gmail.com
- 🔗 [GitHub](https://github.com/NikitaRakhade29)
- 🔗 [LinkedIn](https://linkedin.com/in/nikita-rakhade)

---

> ⭐ If you found this project helpful, please give it a star!
