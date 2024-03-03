--creating a table
create table Transactions( dateofTransaction Date not null, amountInTransaction Number not null, sentto varchar(100) not null, remarks varchar(255) not null);

--creating a sequence
create sequence transactions_seq Start with 20240001 increment by 1;

--adding column transaction_id
alter table transactions add transaction_id number not null;

--adding sequence and primary key to transaction_id
alter table transactions add constraint transactions_seq primary key(transaction_id);

--inserting records
insert into transactions values('23Feb2024',21000,'sarina','Bills',transactions_seq.nextval);

insert into transactions values('12Feb2024',321560,'ram','water',transactions_seq.nextval);

insert into transactions values('14Feb2024',1560,'ram','home',transactions_seq.nextval);

insert into transactions values('19Feb2024',451560,'ram','electricity',transactions_seq.nextval);

insert into transactions values('7Feb2024',451550,'akash','electricity',transactions_seq.nextval);

insert into transactions values('27Feb2024',45561550,'akash','rent',transactions_seq.nextval);


--1.Filter based on given range of date
create view Date_Filter as select * from transactions where dateoftransaction between '1Feb2024' and '15Feb2024';
select * from date_filter;

--2.least amount transfered 
create view Min_Amount as select min(amountintransaction) as min_amount from transactions;
select * from min_amount;

--3.Maximum amount transfered
create view Max_Amount as select max(amountintransaction) as max_amount from transactions;
select * from max_amount;

--4.Number of transaction made to a perticular beneficiary
CREATE VIEW total_transaction AS SELECT sentto AS beneficiary,SUM(amountintransaction) AS total_amount,COUNT(*) AS count_transaction FROM transactions GROUP BY sentto;
select * from total_transaction;

--5.Filter Based on particular remarks
create view Filter_remarks as select transaction_id,sentto,amountintransaction from transactions where remarks = 'electricity';
select * from filter_remarks;