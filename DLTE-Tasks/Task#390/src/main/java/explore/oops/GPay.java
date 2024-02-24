package explore.oops;

import java.util.Scanner;

public class GPay extends DebitCard{


    private  DebitCard associatedDebitCard;
    private Integer upiPin;
    private String userName;

    public GPay(DebitCard associatedDebitCard,Integer upiPin,String userName) {
        super();
        this.associatedDebitCard=associatedDebitCard;
        this.upiPin = upiPin;
        this.userName = userName;
    }

    public Integer getUpiPin() {
        return upiPin;
    }

    public void setUpiPin(Integer upiPin) {
        this.upiPin = upiPin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public DebitCard getAssociatedDebitCard(){
        return associatedDebitCard;
    }

    @Override
    public String toString() {
        return "GPay{" +
                "upiPin=" + upiPin +
                ", userName='" + userName + '\'' +
                '}';
    }

    static GPay gPay1=new GPay(debitCard1,1234,"elroy23");
    static GPay gPay2=new GPay(debitCard2,5678,"Razak23");
    static GPay gPay3=new GPay(debitCard3,1234,"Amir24");
    static GPay gPay4=new GPay(debitCard4,1234,"Arjun34");
    static GPay gPay5=new GPay(debitCard5,1234,"Rajkumar12");
    static GPay[] gPay={gPay1,gPay2,gPay3,gPay4,gPay5};


    public static void main(String[] args){

        System.out.println("---------Welcome to myBank ---------");
        Scanner input=new Scanner(System.in);
        int choice;
        while (true){
            System.out.println("Choose the service you would like to perform");
            System.out.println("1. Do you want to Withdraw the Money\n2. Would you like pay bills using GPay\n3. Exit");
            choice=input.nextInt();
            switch (choice){
                case 1:
                    DebitCard.withdraw();
                    break;
                case 2:
                    payBills();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter the correct choice");
            }
        }

    }

    private static void payBills() {
        Scanner input=new Scanner(System.in);
        System.out.println("enter the Biller name");
        String billerName= input.next();
        System.out.println("enter the billing amount ");
        Double billedAmount=input.nextDouble();
        System.out.println("eenter the type of bill");
        String billType=input.next();
        System.out.println("enter the upi pin");
        Integer upi=input.nextInt();
        GPay selectedGPayUser=null;
        for(GPay each: gPay){
            if (each.getUpiPin().equals(upi)){
                selectedGPayUser=each;
                break;
            }
        }
        if (selectedGPayUser!=null){
            DebitCard associatedDebitCard=selectedGPayUser.getAssociatedDebitCard();
            if (billedAmount<=associatedDebitCard.getAssociatedAccount().getAccountBalance()){
                System.out.println("Amount of "+billedAmount+" of type"+billType+" is tranferred to "+billerName);
            }else {
                System.out.println("transfer is not possible due to low balance ");
            }
        }else {
            System.out.println("wrong pin");
        }

    }


}
