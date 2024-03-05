package org.example;

import java.io.IOException;

public class CreditCardMain {
    public static void main(String[] args) throws IOException {
        MyBankDatabase myBankDatabase=new MyBankDatabase();
        myBankDatabase.createCreditCard();
    }
}
