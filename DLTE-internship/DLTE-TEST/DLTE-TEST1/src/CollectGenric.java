import java.util.*;

public class CollectGenric {
        public static void main(String[] args) {
//                Arraylist
//            ArrayList<String> customerNames = new ArrayList<>();
//
//            customerNames.add("John Doe");
//            customerNames.add("Alice Smith");
//            customerNames.add("Bob Johnson");
//
//            for (String name : customerNames) {
//                System.out.println(name);
//            }
//            System.out.println(customerNames.get(0));
//
//
////LinkedList
//        LinkedList<String> transactionHistory = new LinkedList<>();
//
//        transactionHistory.add("Deposit $1000");
//        transactionHistory.add("Withdraw $500");
//        transactionHistory.add("Transfer $200");
//
//        for (String transaction : transactionHistory) {
//            System.out.println(transaction);
//        }
//        System.out.println(transactionHistory.get(2));
//
// Hashset
//                HashSet<Integer> accountNumbers = new HashSet<>();
//                accountNumbers.add(1005);
//                accountNumbers.add(1001);
//                accountNumbers.add(1008);
//
//                accountNumbers.add(1002); // This won't be added
//                for (int accountNumber : accountNumbers) {
//                        System.out.println(accountNumber);
//                }
//                System.out.println(accountNumbers);
//
//                int accountToCheck = 10;
//                if (accountNumbers.contains(accountToCheck)) {
//                        System.out.println("Account " + accountToCheck + " exists in the set.");
//                } else {
//                        System.out.println("Account " + accountToCheck + " does not exist in the set.");
//                }
//Treeset
                TreeSet<Integer> accountNumbers = new TreeSet<>();
                accountNumbers.add(1003);
                accountNumbers.add(1001);
                accountNumbers.add(1002);
                for (int accountNumber : accountNumbers) {
                        System.out.println(accountNumber);
                }
                int accountToCheck = 1001;
                if (accountNumbers.contains(accountToCheck)) {
                        System.out.println("Account " + accountToCheck + " exists in the set.");
                } else {
                        System.out.println("Account " + accountToCheck + " does not exist in the set.");
                }
//Hashmap
//                HashMap<Integer, Double> accountBalances = new HashMap<>();
//
//                accountBalances.put(1001, 5000.00);
//                accountBalances.put(1002, 7000.00);
//                accountBalances.put(1003, 3000.00);
//
//
//                accountBalances.put(1001,3000.00);//Overwrites the previous value associated with key 1001
//                accountBalances.put(1001,null);//allowed
//
//                for (int accountNumber : accountBalances.keySet()) {
//                        System.out.println("Account Number: " + accountNumber + ", Balance: " + accountBalances.get(accountNumber));
//                }
//                System.out.println(accountBalances.get(1002));
//TreeMap
//                TreeMap<Integer, Double> accountBalances = new TreeMap<>();
//
//                accountBalances.put(1003, 3000.00);
//                accountBalances.put(1001, 5000.00);
//                accountBalances.put(1002, 7000.00);
//
//                accountBalances.put(1001,null);
//
//                for (int accountNumber : accountBalances.keySet()) {
//                        System.out.println("Account Number: " + accountNumber + ", Balance: " + accountBalances.get(accountNumber));
//                }
//                System.out.println(accountBalances.get(1001));

                //Non generic
                ArrayList transactionAmounts = new ArrayList();
                transactionAmounts.add(1000.0);
                transactionAmounts.add("Withdraw $500");
                System.out.println(transactionAmounts.get(0));

                HashMap customerDetails = new HashMap();
                customerDetails.put(1001, "Alice");
                customerDetails.put("Bob", 7000.0);// Incorrect: Mixing key types
                System.out.println(customerDetails.keySet());
        }


}
