--Before Normalization

CREATE TABLE users_before_norm (
    username VARCHAR2(255),
    upi NUMBER,
    mobilenumber VARCHAR2(20),
    email VARCHAR2(255),
    wallet_type VARCHAR2(50),
    recharged_date DATE,
    recharged_provider VARCHAR2(100),
    recharged_to VARCHAR2(255),
    recharged_amount NUMBER
);


-- First Normal Form

CREATE TABLE User_first_norm (
    username VARCHAR2(255) PRIMARY KEY,
    upi NUMBER,
    mobilenumber VARCHAR2(20),
    email VARCHAR2(255),
    wallet_type VARCHAR2(50)
);

CREATE TABLE Recharge_first_norm (
    username VARCHAR2(255),
    recharged_to VARCHAR2(255),
    recharged_amount NUMBER,
    recharged_date DATE,
    recharged_provider VARCHAR2(100),
    FOREIGN KEY (username) REFERENCES User_first_norm(username)
);


--Second Normal Form

CREATE TABLE User_second_norm (
    user_id VARCHAR2(255) PRIMARY KEY,
    username VARCHAR(255),
    mobilenumber VARCHAR(255),
    email VARCHAR(255)
);

CREATE TABLE Upi_second_norm (
    upi_id NUMBER PRIMARY KEY,
    user_id VARCHAR2(255), 
    upi VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES User_second_norm(user_id),
    UNIQUE (user_id, upi)
);

CREATE TABLE Wallet_second_norm (
    wallet_id NUMBER PRIMARY KEY,
    upi_id INT,
    wallet_type VARCHAR(255),
    FOREIGN KEY (upi_id) REFERENCES Upi_second_norm(upi_id)
);

CREATE TABLE Recharge_second_norm (
    recharge_id NUMBER PRIMARY KEY,
    upi_id INT,
    recharged_amount DECIMAL(10, 2),
    recharged_date DATE,
    recharged_provider VARCHAR(255),
    FOREIGN KEY (upi_id) REFERENCES Upi_second_norm(upi_id)
);

-- Since there are no transitive dependencies the table with 2NF is already in 3NF 
