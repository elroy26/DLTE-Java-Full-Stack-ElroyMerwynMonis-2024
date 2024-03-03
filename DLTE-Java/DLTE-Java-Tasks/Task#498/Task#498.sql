--SQL> connect /as sysdba
--Connected.
--SQL> alter user SYS identified by root;
--User altered.

--Creating five user
create user user1 identified by user1;
grant connect to user1;

create user user2 identified by user2;
grant connect to user2;

create user user3 identified by user3;
grant connect to user3;

create user user4 identified by user4;
grant connect to user4;

create user user5 identified by user5;
grant connect to user5;

--exit
--sqlplus
--Enter user-name: Hello
--Enter password:
--Connected to:
--Oracle Database 11g Express Edition Release 11.2.0.2.0 - Production

--Granting priveleges to 5 users

GRANT SELECT ON Transactions TO user1;
GRANT delete ON Transactions TO user2;
GRANT select ON Transactions TO user3;
GRANT insert ON Transactions TO user4;
GRANT update ON Transactions TO user5;
--Grant succeeded.