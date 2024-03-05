package app.mybank;

import app.mybank.entity.Account;
import app.mybank.middleware.TransactionFileRepository;
import app.mybank.remotes.TransactionRepository;
import app.mybank.services.TransactionService;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    private static TransactionService transactionService=new TransactionService();


    private static Scanner scanner=new Scanner(System.in);
    public static void main( String[] args )
    {   int option;
        System.out.println("Welcome to MyBank");
        System.out.println("Enter the user name");
        String userName=scanner.nextLine();
        System.out.println("Enter the password");
        String password=scanner.next();

//        transactionService.addAccount();
        if (transactionService.verifyAccount(userName, password)){
            while (true){
                System.out.println("4. View Transaction History");
                int choice=scanner.nextInt();
                switch (choice){
                    case 4:
                        do{
                            System.out.println("1. View All Transaction");
                            System.out.println("1. View Transactions by Date");
                            System.out.println("1. View Transactions by Amount");
                            System.out.println("1. View by Type of Transaction ");
                            option=scanner.nextInt();
                            switch (option){
                                case 1:
                                    transactionService.viewTransaction(userName);
                                    break;
                                case 2:
                                    System.out.println("Enter the range:(MM/DD/YYYY)\nStart Date:");
                                    String startDate=scanner.next();
                                    System.out.println("End Date:");
                                    String endDate=scanner.next();
                                    transactionService.findByDate(startDate,endDate);
                                    break;
                                default: return;
                            }
                        }while (true);

                }

            }
        }





    }

}
