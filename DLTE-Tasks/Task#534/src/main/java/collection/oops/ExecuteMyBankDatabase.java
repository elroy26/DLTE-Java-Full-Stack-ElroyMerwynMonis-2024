package collection.oops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ExecuteMyBankDatabase {
    public static void main(String[] args) {
        CreditCard creditCard=new CreditCard();
        MyBankDatabase<CreditCard> cardMyBankDatabase=new MyBankDatabase<>();
        MyBankDatabase<Transactions> transactionsMyBankDatabase=new MyBankDatabase<>();

        List<CreditCard> cardList= new ArrayList<>();
        cardList.add(new CreditCard(87326343245L,123,1234,new Date()));
        cardList.add(new CreditCard(23412342334L,456,2367,new Date()));
        cardList.add(new CreditCard(12495764244L,765,5934,new Date()));

        List<Transactions> transactionsList=new ArrayList<>();
        transactionsList.add(new Transactions(new Date(),2314.234,"elroy","water"));
        transactionsList.add(new Transactions(new Date(),873342.23,"vijay","education"));
        transactionsList.add(new Transactions(new Date(),31413.34,"arun","loan"));

        cardMyBankDatabase.myObjects=cardList;
        transactionsMyBankDatabase.myObjects=transactionsList;

        System.out.println(cardMyBankDatabase.insertNewRecord(cardList.get(0)));
        System.out.println(cardMyBankDatabase.insertNewRecord(cardList.get(1)));
        System.out.println(cardMyBankDatabase.insertNewRecord(cardList.get(2)));

        System.out.println(transactionsMyBankDatabase.insertNewRecord(transactionsList.get(0)));
        System.out.println(transactionsMyBankDatabase.insertNewRecord(transactionsList.get(0)));
        System.out.println(transactionsMyBankDatabase.insertNewRecord(transactionsList.get(0)));

        System.out.println(cardMyBankDatabase.read(0));
        System.out.println(cardMyBankDatabase.read(1));
        System.out.println(cardMyBankDatabase.read(2));

        System.out.println(cardMyBankDatabase.delete(1));


        System.out.println(transactionsMyBankDatabase.read(0));

    }
}
