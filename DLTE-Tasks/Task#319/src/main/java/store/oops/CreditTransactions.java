package store.oops;

import java.util.Date;
import java.util.Scanner;

import static java.lang.System.exit;

public class CreditTransactions {
    public static void main(String[] args){
        Scanner input=new Scanner(System.in);
        CreditCard[] transactionsDetails= {
                new CreditCard(new Date(2024, 1, 15), 2000.0, "akash", "Education"),
                new CreditCard(new Date(2024,1,5),3456.0,"akash","Bills"),
                new CreditCard(new Date(2024, 1, 20), 300.0, "arun", "Bills"),
                new CreditCard(new Date(2024, 1, 25), 400.0, "arun", "Family"),
                new CreditCard(new Date(2024, 1, 30), 5007.0, "akash", "Emergency"),
                new CreditCard(new Date(2024, 2, 6), 350.0, "varun", "Education")
        };

        CreditTransactions transactions=new CreditTransactions();

        while (true){
            System.out.println("------Welcome to Transactions folder---------");
            System.out.println("1. Filter transactions based on date");
            System.out.println("2. Least amount transferred");
            System.out.println("3. Maximum amount Transferred");
            System.out.println("4. Number of transactions made to particular beneficiary");
            System.out.println("5. Filter based on particular remarks ");
            System.out.println("6. Sort the Beneficiary name in descending order");
            System.out.println("7. Sort the amount based in ascending order");
            int choice=input.nextInt();

            switch (choice){
                case 1:

                    transactions.FilterDate(transactionsDetails);
                    break;
                case 2:
                    transactions.MinAmount(transactionsDetails);
                    break;
                case 3:
                    transactions.MaxAmount(transactionsDetails);
                    break;
                case 4:
                    System.out.println("enter the number name of the beneficiary");
                    String name=input.next();
                    transactions.NoOfTransactions(transactionsDetails,name);
                    break;
                case 5:
                    System.out.println("entre the name of remark");
                    String remark=input.next();
                    transactions.FilterRemarks(transactionsDetails,remark);
                    break;
                case 6:
                    transactions.SortBenificiary(transactionsDetails);
                    break;
                case 7:
                    transactions.SortAmount(transactionsDetails);
                    break;
                default:exit(0);
            }

        }
    }



    private static void SortAmount(CreditCard[] transactionsDetails) {
        boolean swapped;
        int n = transactionsDetails.length;

        do {
            swapped = false;
            for (int i = 0; i < n - 1; i++) {
                if (transactionsDetails[i].getAmountInTransaction().compareTo(transactionsDetails[i + 1].getAmountInTransaction()) > 0) {
                    CreditCard temp = transactionsDetails[i];
                    transactionsDetails[i] = transactionsDetails[i + 1];
                    transactionsDetails[i + 1] = temp;
                    swapped = true;
                }
            }
            n--;
        } while (swapped);


        System.out.println("Sorted transactions by beneficiary name in descending order:");
        for (CreditCard card : transactionsDetails) {
            System.out.println(card);
        }

    }

    public static void SortBenificiary(CreditCard[] transactionsDetails) {

        boolean swapped;
        int n = transactionsDetails.length;

        do {
            swapped = false;
            for (int i = 0; i < n - 1; i++) {
                if (transactionsDetails[i].getToRecipient().compareTo(transactionsDetails[i + 1].getToRecipient()) < 0) {
                    CreditCard temp = transactionsDetails[i];
                    transactionsDetails[i] = transactionsDetails[i + 1];
                    transactionsDetails[i + 1] = temp;
                    swapped = true;
                }
            }
            n--;
        } while (swapped);


        System.out.println("Sorted transactions by beneficiary name in descending order:");
        for (CreditCard card : transactionsDetails) {
            System.out.println(card);
        }
    }

    private static void FilterRemarks(CreditCard[] transactionsDetails, String remark) {

        double total=0;
        for (CreditCard each: transactionsDetails){
            if(each.getRemarks().equals(remark)){
                total+=each.getAmountInTransaction();
            }
        }
        System.out.println("Total amount transacted toward the remark"+remark+"  is "+total);
    }

    private static void NoOfTransactions(CreditCard[] transactionsDetails, String name) {
        int count=0;
        String Name="";
        for (CreditCard each: transactionsDetails){
            if(each.getToRecipient().equals(name)){

                count++;
                Name=each.getToRecipient();
            }
        }
        System.out.println("Number of times the transactions has done to"+Name+"  is "+count);
    }

    private static void MaxAmount(CreditCard[] transactionsDetails) {
        String name="";
        double temp=0.0;
        for(CreditCard each: transactionsDetails){


            if(each.getAmountInTransaction()>temp){
                temp=each.getAmountInTransaction();
                name=each.getToRecipient();
            }
        }
        System.out.println("maximun amount transferred to "+name+"is:"+temp);
    }

    private static void MinAmount(CreditCard[] transactionsDetails) {
        String name="";
        double temp=999999;
        for(CreditCard each: transactionsDetails){
            if(each.getAmountInTransaction()<temp){
                temp=each.getAmountInTransaction();
                name=each.getToRecipient();
            }
        }
        System.out.println("manimum amount transferred to "+name+"is:"+temp);
    }

    public static void FilterDate(CreditCard[] transactionsDetails) {
        for(CreditCard each: transactionsDetails){
            if (each.getDateOfTransaction().after(new Date(2024,1,1)) && each.getDateOfTransaction().before(new Date(2024,1,31)))
            {
                System.out.println("Date of Transaction:"+each.getDateOfTransaction()+
                        "\nAmount transacted:"+each.getAmountInTransaction()+
                        "\nRecepiant:"+each.getToRecipient()+
                        "\nRemarks:"+each.getRemarks()+
                        "\n-----------------------");
            }
        }
    }
}
