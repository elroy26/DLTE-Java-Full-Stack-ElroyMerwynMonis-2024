package store.oops;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

public class BondDetails {
    public static void main(String[] args) {

        //inititaliazation
        BankBonds[] myBonds = {
                new BankBonds(1200020.0, 0.7, true, "elroy", 10),
                new BankBonds(1300020.0, 0.56, false, "akash", 14),
                new BankBonds(2200020.0, 0.89, false, "arun", 15),
                new BankBonds(7205420.0, 0.34, true, "varun", 17),
                new BankBonds(4540020.0, 0.98, true, "manoj", 10),
        };

        //to print the bond details
        BondDetails details = new BondDetails();
        for (BankBonds each:myBonds ){
            System.out.println("Bond Details:"+each);
        }
        System.out.println("bonds with high interest rate");

        //to find the highest bond interest
        double temp=0;
        for(BankBonds each: myBonds){

            if(each.getInterestRate()>temp){
                temp=each.getInterestRate();
            }
        }
        System.out.println("highest bond interest rate "+temp);
    }



}