package basic.services;

import java.util.Scanner;


public class ValidateCarLoan {
    public static void main(String[] args) {
        //Initialization
        String borrowerName="", borrowerPan="", borrowerAddress="", borrowerEmail="", borrowerIncomeType="";
        String mobileNumber="", aadhaar="";
        Scanner scanner=new Scanner(System.in);
        //Inputs
        System.out.println("-----------------Welcome to MyBank--------------------");

        System.out.println("Fill your name ");
        borrowerName=scanner.nextLine();
        while (!borrowerName.matches("^[a-zA-Z]+$")){
            System.out.println("Invalid name Retype name.");
            borrowerName=scanner.nextLine();

        }

        System.out.println("Fill your aadhaar number");
        aadhaar=scanner.next();
        while (!aadhaar.matches("\\d{12}")){
            System.out.println("Invalid aadhaar Retype aadhaar.");
            aadhaar=scanner.nextLine();
        }

        System.out.println("Enter the PAN ");
        borrowerPan= scanner.next();
        while (!borrowerPan.matches("[A-Z]{5}[0-9]{4}[A-Z]")) {
            System.out.println("Invalid PAN number. Retype PAN number:");
            borrowerPan = scanner.next();
        }

        System.out.println("Let us know Income type(Salaried/self employed)");
        borrowerIncomeType= scanner.next();

        System.out.println("Mention the mobile number ");
        mobileNumber=scanner.next();
        while (!mobileNumber.matches("\\d{10}")) {
            System.out.println("Invalid mobile number. Please enter a 10-digit mobile number:");
            mobileNumber = scanner.next();
        }

        System.out.println("Enter the email address");
        borrowerEmail= scanner.next();
        while (!borrowerEmail.matches("^[a-zA-Z0-9]@(?:[a-zA-Z]{3)+[a-zA-Z]{2,7}$")){
            System.out.println("Invalid email. Please enter a valid email");
            borrowerEmail = scanner.next();
        }

        System.out.println("Dear "+borrowerName+" Thanks for showing interest on taking car loan in MyBank your application has submitted and further details will be mailed to you "+borrowerEmail+" or SMS to "+mobileNumber);
    }
}
