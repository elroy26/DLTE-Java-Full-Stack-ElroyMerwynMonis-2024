package basic.service;

import java.util.Scanner;

public class NoOfDebits {
    public static void main(String[] args){
        //Initialization
        int noOfTransactions=9,noOfDebit=0;
        double accountBalance;
        Scanner input=new Scanner(System.in);
        //Inputs
        System.out.println("Enter your account balance of the day");
        accountBalance=input.nextDouble();
        while (noOfTransactions>0){
            System.out.println("Enter your account balance of the day");
            double temp=input.nextDouble();

            //Process
            if(accountBalance>temp){
                accountBalance=temp;
                noOfDebit++;
            }


            noOfTransactions--;

        }
        System.out.println("number of debits is:"+noOfDebit);
    }
}
