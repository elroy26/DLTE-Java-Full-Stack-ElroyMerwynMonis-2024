package basic.service;

import java.util.Scanner;

public class SipCalculator {
    //Initialization
    static double monthlyInvestment, expectedReturnRate, timePeriod;
    static double investedAmount, estReturns, totalvaue;

    public static void main(String[] args){

        Scanner input= new Scanner(System.in);
        //Required input
        System.out.println("-----Welcome to the SIP calculator---");
        System.out.println("Enter the monthly investment:");
        monthlyInvestment=input.nextDouble();
        System.out.println("enter the Expected return rate:");
        expectedReturnRate=input.nextDouble();
        System.out.println("enter the time period:");
        timePeriod=input.nextDouble();

        //process
        investedAmount=monthlyInvestment*12;
        expectedReturnRate/=100;
        expectedReturnRate/=(timePeriod*12);
        totalvaue=monthlyInvestment*((Math.pow((1+expectedReturnRate),(timePeriod*12))-1)/expectedReturnRate)*(1+expectedReturnRate);
        estReturns=totalvaue-investedAmount;

        //output
        System.out.println("Invested amount:"+investedAmount);
        System.out.println("estimated returns:"+estReturns);
        System.out.println("total value:"+totalvaue);

    }
}
