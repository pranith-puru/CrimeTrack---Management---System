-- ==========================================
-- CrimeTrack Management System Database
-- Database: CrimeTrackDB
-- ==========================================

DROP DATABASE IF EXISTS CrimeTrackDB;
CREATE DATABASE CrimeTrackDB;
USE CrimeTrackDB;

-- ==========================================
-- Login Table
-- ==========================================

CREATE TABLE Login (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(100) NOT NULL
);

INSERT INTO Login VALUES
('admin', 'admin123');

-- ==========================================
-- Criminal Table
-- ==========================================

CREATE TABLE Criminal (
    criminal_no INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    alias_name VARCHAR(100),
    address VARCHAR(255),
    gender VARCHAR(20),
    aadhaar VARCHAR(20) UNIQUE,
    image VARCHAR(255)
);

INSERT INTO Criminal (name, alias_name, address, gender, aadhaar, image) VALUES
('Ravi Kumar','Ravi','Bangalore','Male','123456789012','ravi.jpg'),
('Mohan Singh','Mohan','Delhi','Male','987654321098','mohan.jpg'),
('Suresh Yadav','Suresh','Mysore','Male','456789123456','suresh.jpg'),
('Ajay Verma','Ajay','Mumbai','Male','789123456789','ajay.jpg'),
('Pawan Kumar','Pawan','Hyderabad','Male','321654987123','pawan.jpg');

-- ==========================================
-- FIR Table
-- ==========================================

CREATE TABLE FIR (
    fir_no VARCHAR(20) PRIMARY KEY,
    criminal_name VARCHAR(100),
    crime VARCHAR(100),
    fir_date DATE,
    status VARCHAR(20)
);

INSERT INTO FIR VALUES
('FIR001','Ravi Kumar','Theft','2024-04-17','Open'),
('FIR002','Mohan Singh','Assault','2024-04-18','Closed'),
('FIR003','Suresh Yadav','Fraud','2024-04-19','Open'),
('FIR004','Ajay Verma','Robbery','2024-04-20','Open'),
('FIR005','Pawan Kumar','Cheating','2024-04-21','Closed');

-- ==========================================
-- Operators Table
-- ==========================================

CREATE TABLE Operators (
    operator_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    role VARCHAR(50),
    contact VARCHAR(15),
    status VARCHAR(20)
);

INSERT INTO Operators (name, role, contact, status) VALUES
('Admin Officer','Administrator','9876543210','Active'),
('Police Operator','Operator','9123456789','Active'),
('Record Manager','Operator','9988776655','Active'),
('FIR Handler','Operator','9001234567','Inactive'),
('Data Entry Operator','Operator','9012345678','Active');

-- ==========================================
-- Dashboard Queries
-- ==========================================

-- Total Criminals
SELECT COUNT(*) AS TotalCriminals FROM Criminal;

-- Total FIRs
SELECT COUNT(*) AS TotalFIRs FROM FIR;

-- Open FIRs
SELECT COUNT(*) AS OpenCases
FROM FIR
WHERE status='Open';

-- Closed FIRs
SELECT COUNT(*) AS ClosedCases
FROM FIR
WHERE status='Closed';

-- Search Criminal by Aadhaar
SELECT * FROM Criminal
WHERE aadhaar='123456789012';

-- View All FIR Records
SELECT * FROM FIR;

-- View All Operators
SELECT * FROM Operators;

-- ==========================================
-- End of CrimeTrackDB.sql
-- ==========================================
