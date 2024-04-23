package com.company;
//final keyword implemetation

public class  Main {
//    private final String name;
//    private final int age;
//
//    public Main(String name, int age) {
//        this.name = name;
//        this.age = age;
//    }
//
//    public void ImmutablePerson(String name, int age) {
//        this.name = name;
//        this.age = age;
//    }

    public static void main(String[] args) {
//        final double PI = 3.14159;
//        final int MAX_SIZE = 100;
//         PI= 1123;

//        final String str = "Hello";
//        str = str + " World";

//        SecondClass obj = new SecondClass();
//        obj.setValue(10);
//        System.out.println(obj.getValue());
    }
//    public void processRequest(final String request) {
//        request ="elroy";
//    }

    public final void performOperation() {

    }

}
final class SecondClass extends Main{

//        public void performOperation(){
//
//        }

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
//
//class ThirdClass extends SecondClass{
//    public static final double PI = 3.14159;
//}
