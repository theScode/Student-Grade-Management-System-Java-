### 🧾 Description
A simple yet powerful **console-based project** built in Java that helps manage student data efficiently.  
It allows you to:
- Add new student records  
- Calculate total marks, average, and grade automatically  
- View all student details  
- Search for a specific student  
- Save and fetch data using a **MySQL database** with **JDBC** integration  

### ⚙️ Technologies Used
- Java (Core + JDBC)
- MySQL Database
- JDBC Connector (MySQL)
- NetBeans / Eclipse IDE

### 🗄️ Database Details
Database Name → `student_management`  
Table Name → `students`

```sql
CREATE DATABASE student_management;
USE student_management;

CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    total INT,
    average DOUBLE,
    grade CHAR(1)
);
