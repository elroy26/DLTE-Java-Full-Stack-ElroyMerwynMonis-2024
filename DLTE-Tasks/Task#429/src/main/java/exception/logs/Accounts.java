package exception.logs;

public class Accounts {
    private Long accountNumber=0L;
    private Double accountBalance;
    private String accountHolder;

    static Accounts accounts1=new Accounts(1234567890L,20000.56,"elroy");
    static Accounts accounts2=new Accounts(1234453733L,260000.56,"Rasazk");
    static Accounts accounts3=new Accounts(2462267890L,460000.56,"Amir");
    static Accounts accounts4=new Accounts(8944567890L,82764824.56,"Arjun");
    static Accounts accounts5=new Accounts(2435767890L,10000.56,"Rajkumar");
    static Accounts[] accounts={accounts1,accounts2,accounts3,accounts4,accounts5};


    public Accounts(Long accountNumber,Double accountBalance, String accountHolder) {
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.accountHolder = accountHolder;
    }

    public Accounts() {

    }



    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    @Override
    public String toString() {
        return "Accounts{" +
                "accountNumber=" + accountNumber +
                ", accountBalance=" + accountBalance +
                ", accountHolder='" + accountHolder + '\'' +
                '}';
    }
}
