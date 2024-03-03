package basics.services;

import java.util.Scanner;

public class MobileBanking {
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        String name,email,phone,aadhaar,panNum,mpin,bankAcc;
        double balance;
        int loginAttempts=0;
        System.out.println("welcome to internet bankng");
        System.out.println("1. Register acccount");
        System.out.println("2. Login");
        int choice=scanner.nextInt();
        while(choice<=2){
            switch (choice){
                case 1:
                    System.out.println("enter the name");
                    name=scanner.next();
                    System.out.println("enter eamil");
                    email=scanner.next();
                    System.out.println("enter mobile number");
                    phone=scanner.next();
                    System.out.println("enter the account number");
                    bankAcc=scanner.next();
                    System.out.println("enter the aadhaar");
                    aadhaar= scanner.next();
                    System.out.println("enter pan");
                    panNum=scanner.next();
                    System.out.println("create mpin");
                    mpin=scanner.next();
                    System.out.println("confirm mpin");
                    String confirm=scanner.next();
                    if(!mpin.equals(confirm)){
                        System.out.println("mpin do not match");
                    }else {
                        System.out.println("account registerd successfully");

                    }
                case 2:
                    System.out.println("enter eamil");
                    email=scanner.next();
                    System.out.println("enter mpin");
                    mpin=scanner.next();
                    System.out.println(("login succesfull"));

            }
        }}
}
