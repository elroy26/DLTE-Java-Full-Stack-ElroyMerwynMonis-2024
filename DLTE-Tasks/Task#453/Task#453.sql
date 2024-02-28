--insert new transaction

create or replace procedure insert_transaction(
    date_of_transaction in date,
    amount_in_transaction in number,
    transaction_to in varchar2,
    transaction_remarks in varchar2,
    transaction_id in number
)
as
begin
    insert into transactions values(date_of_transaction,amount_in_transaction,transaction_to,transaction_remarks,transactions_seq.nextval);
    commit;
EXCEPTION
    when others then
    DBMS_OUTPUT.PUT_LINE('Error occurred during transaction insertion: ' || SQLERRM);
    RAISE;
end insert_transaction;
/



--delete transaction of given TO 

create or REPLACE PROCEDURE delete_transaction(
    transaction_to IN VARCHAR2
)
AS
BEGIN
    DELETE FROM transactions WHERE sentto = transaction_to;
    COMMIT;
EXCEPTION
     WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('sent to ot found: '||transaction_to);
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error due to: '||SQLERRM);
        RAISE;
END delete_transaction;
/


--filter transaction those done for Education
CREATE OR REPLACE PROCEDURE filter_transaction(
    transaction_remarks IN varchar2
)
as
begin
    for trans in (select * from transactions where remarks = transaction_remarks) Loop
    DBMS_output.put_line('Date: ' || TO_CHAR(trans.dateoftransaction, 'DD-MON-YYYY') || 
                             ', Amount: ' || trans.amountintransaction || 
                             ', To: ' || trans.sentto);
    END Loop;
exception
    when NO_DATA_FOUND then
    DBMS_OUTPUT.PUT_LINE('No transactions found for the specified remarks: ' || transaction_remarks);
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error occurred during transaction filtering: ' || SQLERRM);
        RAISE;
END filter_transaction;
/


--Inputs
BEGIN
--insert_transaction('23Feb2023', 130, 'ramir', 'Dinner',transactions_seq.nextval);
--delete_transaction('sarina');
filter_transaction('Education');
end; 
