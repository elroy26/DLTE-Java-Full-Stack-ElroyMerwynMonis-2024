package org.example;

import java.sql.Date;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TransactionSchedulerMain {

    public static void main(String[] args) {
        ArrayList<CreditCard> cardArrayList = new ArrayList<>();
        cardArrayList.add(new CreditCard(new Date(2024, 1, 15), 2000.0, "akash", "Education"));
        cardArrayList.add(new CreditCard(new Date(2024, 1, 5), 3456.0, "akash", "Bills"));
        cardArrayList.add(new CreditCard(new Date(2024, 1, 20), 300.0, "arun", "Bills"));
        cardArrayList.add(new CreditCard(new Date(2024, 1, 25), 400.0, "arun", "Family"));
        cardArrayList.add(new CreditCard(new Date(2024, 1, 30), 5007.0, "akash", "Emergency"));
        cardArrayList.add(new CreditCard(new Date(2024, 2, 6), 350.0, "varun", "Education"));

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(new TransactionResource(cardArrayList), 0, 5, TimeUnit.SECONDS);

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            scheduler.shutdown();
        }
    }


}
