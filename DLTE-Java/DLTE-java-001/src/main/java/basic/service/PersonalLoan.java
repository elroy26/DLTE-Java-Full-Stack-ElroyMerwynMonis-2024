package basic.service;

import java.util.Scanner;

public class PersonalLoan {
    public static void main(String[] args){
        String name="", PanNumber="", Address="",email="", incomeType="";
        Long phone=0L, aadhaar=0L;
        System.out.println("--------welcome to mybank---------");
        Scanner scanner= new Scanner(System.in);
        System.out.println("fill your name");
        name=scanner.nextLine();
        System.out.println("fill ur aadhaar number");
        aadhaar=scanner.nextLong();
        System.out.println("fill ur pan");
        PanNumber=scanner.next();
        System.out.println("let us now ur income type(Salared/self employed");
        incomeType=scanner.nextLine();
        System.out.println("mention ur mobile number");
        phone=scanner.nextLong();
        System.out.println("enter ur email address");
        email=scanner.next();
        System.out.println("Dear"+name+"Thanks for showind interest on taking the cat loan in my bank your appplication is submitted and further details will ve mailed to yoy"+email+"and your phpne"+phone);


    }
}
