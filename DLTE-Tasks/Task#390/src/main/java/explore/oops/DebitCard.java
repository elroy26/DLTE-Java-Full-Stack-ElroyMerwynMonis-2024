package explore.oops;

import java.util.Scanner;

public class DebitCard {

    private Long cardNumber = 0L;
    private Integer cardPin;
    private Accounts associatedAccount;

    static Accounts accounts1=new Accounts(1234567890L,20000.56,"elroy");
    static Accounts accounts2=new Accounts(1234453733L,260000.56,"Rasazk");
    static Accounts accounts3=new Accounts(2462267890L,460000.56,"Amir");
    static Accounts accounts4=new Accounts(8944567890L,82764824.56,"Arjun");
    static Accounts accounts5=new Accounts(2435767890L,10000.56,"Rajkumar");
    static Accounts[] accounts={accounts1,accounts2,accounts3,accounts4,accounts5};


    static DebitCard debitCard1 = new DebitCard(accounts1, 961234691238L, 1234);
    static DebitCard debitCard2 = new DebitCard(accounts2, 961235253238L, 9840);
    static DebitCard debitCard3 = new DebitCard(accounts3, 961223455238L, 1413);
    static DebitCard debitCard4 = new DebitCard(accounts4, 231434691238L, 5433);
    static DebitCard debitCard5 = new DebitCard(accounts5, 235334691238L, 7353);
    static DebitCard[] debitCards = {debitCard1, debitCard2, debitCard3, debitCard4, debitCard5};

    public DebitCard() {
    }

    public DebitCard(Accounts associatedAccount, Long cardNumber, Integer cardPin) {
        this.associatedAccount = associatedAccount;
        this.cardNumber = cardNumber;
        this.cardPin = cardPin;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getCardPin() {
        return cardPin;
    }

    public void setCardPin(Integer cardPin) {
        this.cardPin = cardPin;
    }

    public Accounts getAssociatedAccount() {
        return associatedAccount;
    }

    protected static void withdraw() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the amount to be withdrawn");
        Double amount = input.nextDouble();
        System.out.println("Enter the Debit pin");
        Integer debitPin = input.nextInt();
        DebitCard selectedDebitUser = null;

        for (DebitCard each : debitCards) {
            if (each.getCardPin().equals(debitPin)) {
                selectedDebitUser = each;
                break;
            }
        }

        if (selectedDebitUser!= null) {
            Accounts associatedAccount=selectedDebitUser.getAssociatedAccount();
            if (amount <= associatedAccount.getAccountBalance()) {
                System.out.println("Withdrawal of " + amount + " is Successful");
            } else {
                System.out.println("Withdrawal amount is more than the account balance ");
            }
        } else {
            System.out.println("Invalid debit pin");
        }
    }
}

