package exception.logs;

import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GPay extends Accounts {
    private  Accounts associatedAccounts;
    private Integer upiPin;
    private String userName;

    public GPay(Accounts associatedAccounts,Integer upiPin,String userName) {
        super();
        this.associatedAccounts=associatedAccounts;
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

    public Accounts getAssociatedAccounts() {
        return associatedAccounts;
    }



    @Override
    public String toString() {
        return "GPay{" +
                "upiPin=" + upiPin +
                ", userName='" + userName + '\'' +
                '}';
    }

    static ResourceBundle resourceBundle= ResourceBundle.getBundle("application");
    static Logger logger=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    static GPay gPay1=new GPay(accounts1,1234,"elroy23");
    static GPay gPay2=new GPay(accounts2,5678,"Razak23");
    static GPay gPay3=new GPay(accounts3,1234,"Amir24");
    static GPay gPay4=new GPay(accounts4,1234,"Arjun34");
    static GPay gPay5=new GPay(accounts5,1234,"Rajkumar12");
    static GPay[] gPay={gPay1,gPay2,gPay3,gPay4,gPay5};


    public static void main(String[] args) {

        System.out.println("---------Welcome to myBank ---------");
        Scanner input = new Scanner(System.in);
        System.out.println("enter the Biller name");
        String billerName = input.next();
        System.out.println("enter the billing amount ");
        Double billedAmount = input.nextDouble();
        System.out.println("eenter the type of bill");
        String billType = input.next();

        logger.log(Level.INFO,resourceBundle.getString("members.init"));
        GPay selectedGPayUser = null;
        int attempts=0;
        boolean pinMatched=false;
        while (!pinMatched&&attempts<5) {
            try {
                System.out.println("enter the upi pin");
                Integer upi = input.nextInt();
                for (GPay each : gPay) {
                    if (each.getUpiPin().equals(upi)) {
                        selectedGPayUser = each;
                        pinMatched=true;
                        break;
                    }
                }
                if (!pinMatched){
                    throw new MyBankException();
                }

            } catch (MyBankException myBankException) {
                logger.log(Level.WARNING, myBankException.toString());
                attempts++;
                System.out.println("Attempts Left "+(5-attempts));
                if(attempts>=5){
                    logger.log(Level.SEVERE,resourceBundle.getString("max.attempts"));
                    break;
                }
            }
        }

        if (selectedGPayUser != null) {
            Accounts associatedAccounts = selectedGPayUser.getAssociatedAccounts();
            if (billedAmount <= associatedAccounts.getAccountBalance()) {
                System.out.println("Amount of " + billedAmount + " of type " + billType + " is tranferred to " + billerName + ". Remaining account balance is " + ((associatedAccounts.getAccountBalance()) - billedAmount));
                associatedAccounts.setAccountBalance((associatedAccounts.getAccountBalance()) - billedAmount);
            } else {
                System.out.println("transfer is not possible due to low balance ");
            }
        }

    }
}
