package basic.service;
import java.util.*;
public class FdCalculator {
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        int years;
        long principle;
        double interest;
        System.out.println("Hello welcome to fd calculator");
        System.out.println("enter amount to calculte the interest");
        principle=scanner.nextLong();
        System.out.println("enter the interest rate");
        interest=scanner.nextDouble();
        System.out.println("enter time duration in yesars");
        years=scanner.nextInt();
        System.out.println("total interest to be gaied after"+years+"years is "+(principle*interest*years)/100);
        System.out.println("total amount to be gained after"+years+"years is "+(principle+(principle*interest*years)/100));


    }
}
