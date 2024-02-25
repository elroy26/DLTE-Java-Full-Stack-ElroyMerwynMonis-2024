package explore.oops;

import java.util.Date;
import java.util.Scanner;

public class MyBankExecute implements MyBank
{
    private Loan[] myLoans;
    public MyBankExecute(){
        myLoans=new Loan[10];
        myLoans[0] = new Loan(1234567809L, 1232432.0, "2024Feb12", "open", "elroy", 8746372378L);
        myLoans[1] = new Loan(3454567809L, 3002432.0, "2024Feb10", "closed", "razak", 8746372378L);
        myLoans[2] = new Loan(6345567809L, 4002432.0, "2024Feb23", "open", "arun", 8746372378L);
        myLoans[3] = new Loan(3534567809L, 5002432.0, "2024Feb13", "closed", "varun", 8746372378L);
        myLoans[4] = new Loan(7655567809L, 2002432.0, "2024Feb4", "closed", "ajay", 8746372378L);

    }



    public static void main( String[] args )
    {   Loan[] myLoans = new Loan[10];

        MyBankExecute execute=new MyBankExecute();
        Scanner input=new Scanner(System.in);
        System.out.println("------Welcome to MyBank Loans------");
        while (true){
            System.out.println("enter the action you would like to perform");
            System.out.println("1. Add new loan");
            System.out.println("2. Check available Loans");
            System.out.println("3. check closed loans");
            int choice=input.nextInt();
            switch (choice){
                case 1:
                    execute.addNewLoan();
                    break;
                case 2:
                    execute.availableLoans();
                    break;
                case 3:
                    execute.closedLoans();
                    break;
                case 4:System.exit(0);
                default:
                    System.out.println("invalid choice");

            }
        }

    }

    @Override
    public void addNewLoan() {

        Scanner input=new Scanner(System.in);
        //loanNumber, loanAmount, loanDate, loanStatus(Open/Closed), borrowerName, borrowerContact;
        System.out.println("Enter the Loan Number");
        Long number=input.nextLong();
        System.out.println("Enter the Loan Amount");
        Double amount=input.nextDouble();
        System.out.println("Enter the Loan Date");
        String date=input.next();
        System.out.println("Enter the Loan Status");
        String status=input.next();
        System.out.println("Enter the Borrower Name");
        String borrowerName=input.next();
        System.out.println("Enter the Borrower Contact");
        Long contact=input.nextLong();

        int index = 0;
        while (index < myLoans.length && myLoans[index] != null) {
            index++;
        }
        if (index < myLoans.length) {
            myLoans[index] = new Loan(number, amount, date, status, borrowerName, contact);
            System.out.println("Loan added successfully!");
        } else {
            System.out.println("Cannot add loan. Loans array is full.");
        }



    }

    @Override
    public void availableLoans() {
        System.out.println("Available loans are\n");

        for (Loan each:myLoans){
            if (each!=null&&each.getLoanStatus().equalsIgnoreCase("Open")){
                System.out.println(each);
            }

        }

    }

    @Override
    public void closedLoans() {
        System.out.println("Closed loans are:");
        for (Loan each:myLoans){

                if (each!=null&&each.getLoanStatus().equalsIgnoreCase("Closed")){
                    System.out.println(each);
                }
            }



    }
}
