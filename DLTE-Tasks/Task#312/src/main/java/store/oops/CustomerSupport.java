package store.oops;

import java.util.Date;
import java.util.Scanner;

public class CustomerSupport {
    public static void main(String[] args) {
//        CreditCard[] myBank=new CreditCard[10];

        CreditCard[] myBank={
                new CreditCard(8765678765678L,"Razak Mohamed S",new Date(2034,12,30),555,100000,new Date(2024,3,11),new Date(2024,03,30),1111),
                new CreditCard(2343234345445L,"Shreyas",new Date(2029,1,4),134,50000,new Date(2024,3,2),new Date(2024,03,22),9999),
                new CreditCard(8765678764545L,"Sanath",new Date(2031,5,15),955,600000,new Date(2024,3,10),new Date(2024,03,11),9864),
                new CreditCard(1234565456767L,"Akash",new Date(2028,8,11),767,200000,new Date(2024,3,5),new Date(2024,03,29),1945),
        };

        CustomerSupport support=new CustomerSupport();
        Scanner input=new Scanner(System.in);

        System.out.println("enter the credit card limit");
        int limit=input.nextInt();
        support.filter(myBank, limit);

        System.out.println("enter the day of bill payment");
        int day=input.nextInt();
        support.billPaymentDay(myBank, day);

        System.out.println("enter the correct pin if pin updation is required");
        Integer pincode=input.nextInt();
        support.pinUpdate(myBank,pincode);

        support.updateCreditLimit(myBank);

    }

    public void updateCreditLimit(CreditCard[] myBank) {
        for (CreditCard each: myBank){
            if (each.getDateOfBillGeneration().getDate()==5){
                double limit=(each.getCreditCardLimit())*0.50;
                System.out.println("Congradulation "+each.getCreditCardHolder()+" your credit card limit has been increased to "+(limit+each.getCreditCardLimit())+" from "+each.getCreditCardLimit());
            }
        }
    }

    public void pinUpdate(CreditCard[] customer, Integer pincode) {
            for (CreditCard each: customer){
                if(pincode.equals(each.getCreditCardPin())) {
                    System.out.println("enter the new pincode");
                    Scanner input = new Scanner(System.in);
                    int newPincode = input.nextInt();
                    each.setCreditCardPin(newPincode);
                    System.out.println("Updated pin succesfully");
                    break;
                }
            }
        }


    public void billPaymentDay(CreditCard[] customer, int day) {
        for (CreditCard each: customer){
            if (each.getDateOfBillPayment().getDate()<=day){
                System.out.println("customer with bill payment day before "+day+" is"+each.getCreditCardHolder());
            }
        }
    }

    public void filter(CreditCard[] customers, int limit) {
        for (CreditCard each: customers){
            if (each.getCreditCardLimit()<=limit){
                System.out.println("credit card users with limit less than "+limit+" is"+each.getCreditCardHolder());
            }
        }
    }



}


