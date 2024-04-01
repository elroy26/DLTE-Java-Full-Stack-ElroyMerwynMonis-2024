CREATE SEQUENCE customer_seq START WITH 20012001 increment by 1;
CREATE TABLE MYBank_web_Customer (
    customer_id number,
    customer_name VARCHAR(255) NOT NULL,
    customer_address VARCHAR(255),
    customer_status VARCHAR(50),
    customer_contact NUMBER, 
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);
alter table MYBANK_web_Customer add constraint  customer_seq primary key(customer_id);

CREATE SEQUENCE kyc_seq START WITH 30013001 increment by 1;
CREATE TABLE MYBank_web_KYC (
    kyc_number number,
    kyc_pan VARCHAR(255) UNIQUE,
    kyc_aadhaar number UNIQUE,
    kyc_status VARCHAR(50),
    customer_id number REFERENCES MYBank_web_Customer(customer_id)
);
alter table MYBANK_web_kyc add constraint kyc_seq primary key(kyc_number);

CREATE SEQUENCE acc_seq START WITH 40014001 increment by 1;
CREATE TABLE MYBank_web_Account (
    account_id number,
    account_type VARCHAR(255) NOT NULL,
    account_number number UNIQUE NOT NULL,
    account_status VARCHAR(50),
    customer_id NUMBER REFERENCES MYBank_web_Customer(customer_id)
);
alter table MYBANK_web_Account add constraint acc_seq primary key(account_id);

CREATE SEQUENCE payee_seq START WITH 50015001 increment by 1;
CREATE TABLE MYBank_web_Payee (
    payee_id number,
    payee_name VARCHAR(255),
    customer_id number REFERENCES MYBank_web_Customer(customer_id),
    account_id number REFERENCES MYBank_web_Account(account_id)
);
alter table MYBANK_web_payee add constraint payee_seq primary key(payee_id);

CREATE SEQUENCE loanavailable START WITH 60016001 increment by 1;
CREATE TABLE MYBank_web_LoanAvailable (
    loan_id number,
    loan_type VARCHAR(255) NOT NULL,
    loan_name VARCHAR(255),
    loan_description VARCHAR(255),
    loan_roi number
);
alter table MYBANK_web_LoanAvailable add constraint loanavailable primary key(loan_id);

CREATE SEQUENCE loanavail START WITH 70017001 increment by 1;
CREATE TABLE MYBank_web_LoanAvailed (
    loan_amount number,
    loan_emi number,
    loan_tenure INT,
    customer_id number REFERENCES MYBank_web_Customer(customer_id),
    loan_app_id number REFERENCES MYBank_web_LoanAvailable(loan_id)
);
ALTER TABLE MYBANK_web_LoanAvailed 
ADD CONSTRAINT loanavail PRIMARY KEY (loan_app_id);

CREATE SEQUENCE DebitCard_seq START WITH 80018001 increment by 1;
CREATE TABLE MYBank_web_DebitCard (
    debitcard_number number,
    debitcard_cvv INT,
    debitcard_pin INT,
    debitcard_expiry DATE,
    debitcard_status VARCHAR(50),
    debitcard_domestic_limit DECIMAL(10,2),
    debitcard_international_limit DECIMAL(10,2),
    account_id number REFERENCES MYBank_web_Account(account_id),
    customer_id number REFERENCES MYBank_web_Customer(customer_id)
);
alter table MYBANK_web_DebitCard add constraint DebitCard_seq primary key(debitcard_number);

CREATE SEQUENCE insurance_available START WITH 90019001 increment by 1;
CREATE TABLE MYBank_web_InsuranceAvailable (
    insurance_id number,
    insurance_type VARCHAR(255) NOT NULL,
    insurance_name VARCHAR(255),
    insurance_key_benefits VARCHAR(255),
    insurance_lifetime INT
);
alter table MYBank_web_InsuranceAvailable add constraint insurance_available primary key(insurance_id);

CREATE SEQUENCE insurance_avail START WITH 21009001 increment by 1;
CREATE TABLE MYBank_web_InsuranceAvailed (
    insurance_availed_id number,
    insurance_type VARCHAR(255) NOT NULL,
    insurance_name VARCHAR(255),
    insurance_key_benefits VARCHAR(255),
    insurance_coverage VARCHAR(255),
    insurance_lifetime INT,
    insurance_premium number,
    customer_id INT REFERENCES MYBank_web_Customer(customer_id),
    insurance_id INT REFERENCES MYBank_web_InsuranceAvailable(insurance_id)
);
alter table MYBank_web_InsuranceAvailed add constraint insurance_avail primary key(insurance_availed_id);

CREATE SEQUENCE transact_seq START WITH 32009001 increment by 1;
CREATE TABLE MYBank_web_Transaction (
    transaction_id number,
    transaction_type VARCHAR(255),
    transaction_from int,
    transaction_to int,
    transaction_date DATE,
    transaction_amount DECIMAL(10,2),
    transaction_status VARCHAR(50),
    account_id INT REFERENCES MYBank_web_Account(account_id)
);
alter table MYBank_web_Transaction add constraint transact_seq primary key(transaction_id);

CREATE SEQUENCE Deposit_seq START WITH 42009001 increment by 1;
CREATE TABLE MYBank_web_DepositsAvailable (
    deposit_id number,
    deposit_name VARCHAR(255),
    deposit_roi DECIMAL(5,2),
    deposit_type VARCHAR(255),
    deposit_description VARCHAR(255)
);
alter table MYBank_web_DepositsAvailable add constraint Deposit_seq primary key(deposit_id);

CREATE SEQUENCE Deposit_avail START WITH 52009001 increment by 1;
CREATE TABLE MYBank_web_DepositsAvailed (
    deposit_avail_id number,
    deposit_id INT REFERENCES MYBank_web_DepositsAvailable(deposit_id),
    deposited_amount DECIMAL(10,2),
    deposited_duration INT,
    deposit_maturity DATE,
    customer_id INT REFERENCES MYBank_web_Customer(customer_id)
);
alter table MYBank_web_DepositsAvailed add constraint Deposit_avail primary key(deposit_avail_id);

