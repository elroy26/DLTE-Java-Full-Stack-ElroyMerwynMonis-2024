package basic.service;

import jdk.net.SocketFlow;

import java.util.Scanner;

public class IncomeTax {

    //Intialization
    static long salary;
    static int choice=0;
    static double tax=0;

    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        //Inputs
        System.out.println("-----Welcome to Income Tax Calculator---------");
        while(true) {
            System.out.println("enter the salary");
            salary = scanner.nextLong();
            System.out.println("Choose the regime");
            System.out.println("1. Old Regime");
            System.out.println("2. New Regime");
            System.out.println("3. exit");
            System.out.println("enter the choice");
            choice = scanner.nextInt();

            //Process

            switch (choice) {
                case 1:
                    oldRegime();
                    break;
                case 2:
                    newRegime();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid Option");


            }

        }
    }

    public static void newRegime() {
        if(salary>0 && salary<=300000){
            System.out.println("no deduction on the salary");

        }else if(salary>300000 && salary<=600000){
            tax= salary*0.05;
            System.out.println("salary deducted after applying tax is"+tax);
        }else if(salary>600000 && salary<=900000){
            tax= salary*0.1;
            System.out.println("salary deducted after applying tax is"+tax);
        }else if(salary>900000 && salary<=1200000){
            tax= salary*0.15;
            System.out.println("salary deducted after applying tax is"+tax);
        }else if(salary>1200000 && salary<=1500000){
            tax= salary*0.2;
            System.out.println("salary deducted after applying tax is"+tax);
        }else if(salary>1500000){
            tax= salary*0.30;
            System.out.println("salary deducted after applying tax is"+tax);
        }
    }

    public static void oldRegime() {
        if(salary>0 && salary<=250000){
            System.out.println("no deduction on the salary");

        }else if(salary>250000 && salary<=500000){
            tax= salary*0.05;
            System.out.println("salary deducted after applying tax is"+tax);
        }else if(salary>500000 && salary<=1000000){
            tax= salary*0.1;
            System.out.println("salary deducted after applying tax is"+tax);
        }else if(salary>1000000 && salary<=1500000){
            tax= salary*0.15;
            System.out.println("salary deducted after applying tax is"+tax);
        }else if(salary>1500000){
            tax= salary*0.30;
            System.out.println("salary deducted after applying tax is"+tax);
        }
    }
}
