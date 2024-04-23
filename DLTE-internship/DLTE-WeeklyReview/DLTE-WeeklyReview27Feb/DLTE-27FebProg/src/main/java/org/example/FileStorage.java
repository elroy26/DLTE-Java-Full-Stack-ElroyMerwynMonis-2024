package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileStorage {
    public static String FILE_NAME = "EmployeeDatabase.txt";
    List <Object> readData=new ArrayList<>();
    public List<Object> readEmployeeFile() {
        try(ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream(FILE_NAME))){
            readData=(List<Object>) objectInputStream.readObject();
            return readData;
        }catch (IOException |ClassNotFoundException e){
            System.out.println("error reading the loans"+e.getMessage());
        }
        return null;
    }

    public void writeEmployeeFile(List<Object> employeeData) {
        try(ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream(FILE_NAME))){
            objectOutputStream.writeObject(employeeData);
            System.out.println("Employee data has been written to file: " + FILE_NAME);
        }catch (IOException e){
            System.out.println("Error writing the loans"+ e.getMessage());
        }
    }
}
