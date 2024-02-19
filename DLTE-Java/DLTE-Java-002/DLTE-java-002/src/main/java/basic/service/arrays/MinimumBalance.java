package basic.service.arrays;

import java.util.Scanner;

public class MinimumBalance {
    public static void main(String[] args){
        //Initializaton
        int noOfCustomers=20;
        double[] customerBalance=new double[noOfCustomers];
        Scanner input=new Scanner(System.in);

        for( int i=0; i<noOfCustomers; i++){
            System.out.println("enter the balance of customer "+(i+1)+":");
            customerBalance[i]=input.nextDouble();
        }

        updateBalances(customerBalance);

        //Output
        System.out.println("updated customer balance");
        for (int i= 0;i<customerBalance.length;i++){
            System.out.println("Customer"+(i+1)+":"+customerBalance[i]);
        }


    }

    //Process
    public static void updateBalances(double[] balance) {
        for (int i= 0;i<balance.length;i++){
            if(balance[i]<10000 ){
                if(balance[i]>=1000 && balance[i]<=4999){
                    balance[i]-=balance[i]*0.05;
                }else if(balance[i]>=5000 && balance[i]<=9999){
                    balance[i]-=balance[i]*0.03;
                }
            }
        }
    }
}
