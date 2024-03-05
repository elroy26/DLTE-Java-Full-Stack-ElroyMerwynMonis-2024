package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MyBankDatabase implements CreditCardActivity {
    public static String FILE_NAME = "creditCard.txt";
    public List<CreditCard> creditCards;
    public MyBankDatabase(){
        creditCards= new ArrayList<>();
    }
//    public ArrayList<CreditCard> getCreditCards() {
//        return (ArrayList<CreditCard>) creditCards;
//    }

    private void writeCardsFile()  {
        try (ObjectOutputStream objectOutputStream= new ObjectOutputStream(new FileOutputStream(FILE_NAME,true))){
            objectOutputStream.writeBytes(String.valueOf(creditCards));
        }catch (IOException e){
            System.out.println("Error writing the credit card"+e.getMessage());
        }
    }

    @Override
    public void createCreditCard() throws IOException {
        Scanner scanner=new Scanner(System.in);
        String choice;
        System.out.println("---Enter the Credit Card details");
       do{
           System.out.println("Enter the card number");
           Long creditCardNumber=scanner.nextLong();
           scanner.nextLine();
           System.out.println("enter the name of card holder");
           String creditCardHolder=scanner.nextLine();
           Date creditCardExpiry= new Date();
           System.out.println("enter the credit card cvv");
           Integer creditCardCvv= scanner.nextInt();
           System.out.println("enter the credit limit");
           Integer creditCardLimit=scanner.nextInt();
           Date dateOfBillGeneration=new Date();
           Date dateOfBillPayment=new Date();
           System.out.println("enter the credit card pin");
           Integer creditCardPin=scanner.nextInt();
           creditCards.add(new CreditCard(creditCardNumber,creditCardHolder,creditCardExpiry,creditCardCvv,creditCardLimit,dateOfBillGeneration,dateOfBillPayment,creditCardPin));

           writeCardsFile();

           System.out.println("Do you want to continue : (Yes/No)");
           choice=scanner.next();
       }while (choice.equalsIgnoreCase("yes"));


    }
}
