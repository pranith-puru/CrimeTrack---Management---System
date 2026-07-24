# 🚔 CrimeTrack Management System

A modern desktop-based Crime Management System developed using **JavaFX**, **JDBC**, and **MySQL**. The application helps police departments efficiently manage criminal records, FIRs, operator information, and crime reports through an intuitive graphical interface.

---

## 📌 Features

- 🔐 Secure Login Authentication
- 📊 Interactive Dashboard
- 👤 Add Criminal Records
- 🔍 Search Criminal by Aadhaar Number
- 📑 FIR Records Management
- 👮 View Criminal Details
- 📈 Reports Dashboard
- 💾 MySQL Database Connectivity using JDBC
- 🎨 Modern JavaFX User Interface
- 🏗️ MVC Architecture

---

## 🛠️ Technologies Used

- Java
- JavaFX
- JDBC
- MySQL
- Eclipse IDE
- CSS
- FXML

---

## 📂 Project Structure

```
CrimeTrack-Management-System
│
├── src/
│   ├── MainApp.java
│   ├── DBConnection.java
│   ├── Controllers
│   └── Models
│
├── resources/
│   ├── FXML Files
│   ├── CSS
│   └── Images
│
└── README.md
```

---

## 🗄️ Database

Database: **MySQL**

Main Tables:

- Login
- Criminal
- FIR
- Operators

The application connects to MySQL using JDBC for storing and retrieving records.

---

## 🚀 Installation

1. Clone the repository

```
git clone https://github.com/pranith-puru/CrimeTrack---Management---System.git
```

2. Open the project in Eclipse IDE.

3. Import the MySQL database.

4. Add the MySQL Connector/J library.

5. Configure the database credentials in `DBConnection.java`.

6. Run `MainApp.java`.

---

## 📋 Modules

- Login
- Dashboard
- Add Criminal
- Search Criminal
- FIR Records
- View Criminals
- Operators
- Reports

---

## 🏛️ Architecture

The project follows the **MVC (Model-View-Controller)** architecture.

- **Model** → MySQL Database
- **View** → JavaFX FXML
- **Controller** → Java Controllers

---

## 🔮 Future Enhancements

- MongoDB (NoSQL) CRUD Operations
- Face Recognition
- Biometric Authentication
- PDF Report Generation
- Cloud Database Integration
- AI-based Crime Analysis

---

## 👨‍💻 Developer

**Pranith C Gowda**

BE – Information Science & Engineering

The National Institute of Engineering, Mysore

---

## 📜 License

This project is licensed under the MIT License.
