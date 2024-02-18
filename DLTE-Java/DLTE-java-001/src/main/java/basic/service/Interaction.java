package basic.service;

//command line interactions: Car loans
/*
personal details : name,aadhaar, pan, address, mobile, email.
Income: salaried, selfemployed: itr
 */

import java.util.Scanner;

public class Interaction {
    public static void main(String[] args){
        String borrowerName="",borrowerPan="", borrowerAddress="", borrowerEmail="", borrowerIncomeType="";
        Long mobileNumber=0L,aadhaar=0L;
        System.out.println("--------welcome to mybank---------");
        Scanner scanner= new Scanner(System.in);
        System.out.println("fill your name");
        borrowerName=scanner.nextLine();
        System.out.println("fill ur aadhaar number");
        aadhaar=scanner.nextLong();
        System.out.println("fill ur pan");
        borrowerPan=scanner.next();
        System.out.println("let us now ur income type(Salared/self employed");
        borrowerIncomeType=scanner.nextLine();
        System.out.println("mention ur mobile number");
        mobileNumber=scanner.nextLong();
        System.out.println("enter ur email address");
        borrowerEmail=scanner.next();

        System.out.println("Dear"+borrowerName+"Thanks for showind interest on taking the cat loan in my bank your appplication is submitted and further details will ve mailed to yoy"+borrowerEmail+"and your phpne"+mobileNumber);
    }
}
