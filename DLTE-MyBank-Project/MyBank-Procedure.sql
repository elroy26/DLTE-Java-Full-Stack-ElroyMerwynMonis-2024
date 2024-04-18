create or replace PROCEDURE insert_insurance_availed(
    p_customer_id INT,
    p_insurance_id INT,
    p_insurance_type VARCHAR2,
    p_insurance_name VARCHAR2,
    p_insurance_key_benefits VARCHAR2,
    p_insurance_lifetime INT,
    p_insurance_premium DECIMAL,
    p_insurance_coverage DECIMAL
) AS 
    v_customer_status VARCHAR2(255);
    v_insurance_count INT;
    v_insurance_id INT;
BEGIN
    -- Check for available insurance id
    SELECT COUNT(*) INTO v_insurance_id FROM mybank_app_insuranceavailable WHERE insurance_id = p_insurance_id;

    -- Check customer status
    SELECT customer_status INTO v_customer_status FROM mybank_web_customer WHERE customer_id = p_customer_id;

    -- Check if the customer has already availed this insurance
    SELECT COUNT(*) INTO v_insurance_count FROM mybank_app_insuranceavailed 
    WHERE customer_id = p_customer_id AND insurance_id = p_insurance_id;

    -- Insert insurance if customer is active and hasn't availed it already
    IF v_customer_status = 'open' AND v_insurance_count = 0 AND v_insurance_id IS NOT NULL THEN
        INSERT INTO mybank_app_insuranceavailed (
            insurance_avail_id,
            customer_id,
            insurance_id,
            insurance_type,
            insurance_name,
            insurance_key_benefits,
            insurance_lifetime,
            insurance_premium,
            insurance_coverage
        ) VALUES (
            INSURANCE_AVAIL_SEQ.nextval,
            p_customer_id,
            p_insurance_id,
            p_insurance_type,
            p_insurance_name,
            p_insurance_key_benefits,
            p_insurance_lifetime,
            p_insurance_premium,
            p_insurance_coverage
        );
        COMMIT;
    ELSIF v_insurance_id IS NULL THEN
        RAISE_APPLICATION_ERROR(-20001, 'Selected Insurance type not available');
    ELSIF v_insurance_count > 0 THEN
        RAISE_APPLICATION_ERROR(-20002, 'Customer has already availed this insurance');
    ELSE
        RAISE_APPLICATION_ERROR(-20003, 'Cannot insert insurance for inactive customer');
    END IF;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20004, 'Customer not found');
END;
