const performValidate = () => {
    var isValid = true;

    var myForm = document.forms['application'];
    const accountNumber = myForm.accountNumber.value.trim();
    const accountHolder = myForm.accountHolder.value.trim();
    const chequeBookType = myForm.chequeBookType.value;
    const dateOfApply = myForm.dateOfApply.value;
    const address = myForm.address.value.trim();
    const signature = myForm.signature.value.trim();
    const contactNumber = myForm.contactNumber.value.trim();
    const email = myForm.email.value.trim();
    const accountType = myForm.accountType.value;

    var accountNumberErr = document.getElementById('accountNumbererr');
    var accountHolderErr = document.getElementById('accountHoldererr');
    var chequeBookTypeErr = document.getElementById('chequeBookTypeerr');
    var dateOfApplyErr = document.getElementById('dateOfApplyerr');
    var addressErr = document.getElementById('addresserr');
    var signatureErr = document.getElementById('signatureerr');
    var contactNumberErr = document.getElementById('contactNumbererr');
    var emailErr = document.getElementById('emailerr');
    var accountTypeErr = document.getElementById('accountTypeerr');
    var termsAndConditionsErr = document.getElementById('termsAndConditionserr');

    try {
        if (!(/[0-9]{12}/).test(accountNumber)) {
            throw "Please enter a valid 12-digit account number";
        }
        accountNumberErr.innerText = '';
    } catch (error) {
        accountNumberErr.innerText = error;
        isValid = false;
    }

    try {
        if (!(/^[a-zA-Z ]+$/).test(accountHolder)) {
            throw "Account holder name must contain only alphabets and spaces";
        }
        accountHolderErr.innerText = '';
    } catch (error) {
        accountHolderErr.innerText = error;
        isValid = false;
    }

    try {
        if (chequeBookType === 'Select type' || chequeBookType === '') {
            throw "Please select cheque book type";
        }
        chequeBookTypeErr.innerText = '';
    } catch (error) {
        chequeBookTypeErr.innerText = error;
        isValid = false;
    }

    try {
        if (!dateOfApply) {
            throw "Please select date of application";
        }
        dateOfApplyErr.innerText = '';
    } catch (error) {
        dateOfApplyErr.innerText = error;
        isValid = false;
    }

    try {
        if (!address) {
            throw "Please enter address";
        }
        addressErr.innerText = '';
    } catch (error) {
        addressErr.innerText = error;
        isValid = false;
    }

    try {
        if (!signature) {
            throw "Please upload signature";
        }
        // You can add additional checks for signature file here
        signatureErr.innerText = '';
    } catch (error) {
        signatureErr.innerText = error;
        isValid = false;
    }

    try {
        if (!(/^\d{10}$/).test(contactNumber)) {
            throw "Please enter a valid 10-digit contact number";
        }
        contactNumberErr.innerText = '';
    } catch (error) {
        contactNumberErr.innerText = error;
        isValid = false;
    }

    try {
        if (!(/^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/).test(email)) {
            throw "Please enter a valid email address";
        }
        emailErr.innerText = '';
    } catch (error) {
        emailErr.innerText = error;
        isValid = false;
    }

    try {
        if (!accountType) {
            throw "Please select account type";
        }
        accountTypeErr.innerText = '';
    } catch (error) {
        accountTypeErr.innerText = error;
        isValid = false;
    }

    try {
        if (!myForm.termsAndConditions.checked) {
            throw "Please agree to the Terms and Conditions";
        }
        termsAndConditionsErr.innerText = '';
    } catch (error) {
        termsAndConditionsErr.innerText = error;
        isValid = false;
    }

    return isValid;
};
