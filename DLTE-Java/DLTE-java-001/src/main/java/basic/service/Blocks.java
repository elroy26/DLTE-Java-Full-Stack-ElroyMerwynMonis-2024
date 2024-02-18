package basic.service;

import java.util.Arrays;

//scopes overloading,execution points

class Traditional {
    static {
        System.out.println("casa, funds, bonds,loans");
        Traditional.main(new Integer[]{1,2,3});
        Traditional.main(new String[]{"tieto"});
        System.out.println("Cli banking");

    }
    public static void main(Integer[] args){
        System.out.println("Cli banking, hdfc");
    }
    public static void main(String[] args){

        System.out.println(Arrays.toString(args));

    }
}

class Facility {
    public static void main(String[] args){
        System.out.println("Cli banking,atm");
    }
}

class Device {
    public static void main(String[] args){
        System.out.println("Cli banking,fianance");
    }
}

