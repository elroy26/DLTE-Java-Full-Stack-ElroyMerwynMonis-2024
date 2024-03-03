--perform trigger operation

before when insert new transaction with null or empty remarks assign some valid remarks

CREATE OR REPLACE TRIGGER remarks_trigger
BEFORE INSERT ON Transactions
FOR EACH ROW
BEGIN
    IF :new.remarks IS NULL OR TRIM(:new.remarks) = '' THEN
        :new.remarks := 'Enter The valid remarks not empty remarks';
    END IF;
END;
/