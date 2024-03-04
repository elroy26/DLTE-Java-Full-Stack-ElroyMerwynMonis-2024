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
    {
        System.out.println("Welcome to MyBank");
        System.out.println("Enter the user name");
        String userName=scanner.nextLine();
        System.out.println("Enter the password");
        String password=scanner.next();

//        transactionService.addAccount();
        if (transactionService.verifyAccount(userName, password)){
            while (true){
                System.out.println("4. view History");
                int choice=scanner.nextInt();
                switch (choice){
                    case 4:
                        transactionService.viewTransaction(userName);
                        break;
                }

            }
        }





    }

}
