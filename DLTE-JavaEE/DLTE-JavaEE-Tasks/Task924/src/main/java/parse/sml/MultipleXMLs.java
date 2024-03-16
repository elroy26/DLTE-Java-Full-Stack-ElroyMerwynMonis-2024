package parse.sml;

import javax.xml.bind.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MultipleXMLs {


    public static void main(String[] args) throws JAXBException, FileNotFoundException {
        List<Transaction> transactionList= Stream.of(
               new Transaction(new Date("01/01/2024"),12341.0,"elroy","water"),
               new Transaction(new Date("02/02/2024"),13341.0,"ajay","education"),
               new Transaction(new Date("02/24/2024"),35341.0,"elroy","gym"),
               new Transaction(new Date("01/13/2024"),234231.0,"aman","hotel"),
               new Transaction(new Date("03/025/2024"),2342311.0,"elroy","water")
        ).collect(Collectors.toList());

        MyTransactions myTransactions=new MyTransactions(transactionList);
        Transaction transaction=new Transaction();
        JAXBContext context=JAXBContext.newInstance(MyTransactions.class);
//        Marshaller marshaller = context.createMarshaller();
//        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
//        marshaller.marshal(myTransactions,new FileOutputStream("Transactions.xml"));
//        System.out.println("XML has built");

        Unmarshaller unmarshaller=context.createUnmarshaller();
        MyTransactions myLists = (MyTransactions) unmarshaller.unmarshal(new FileInputStream("Transactions.xml"));
        myLists.getTransactions().stream()
                .filter(transaction1 -> transaction1.getToRecipient().equalsIgnoreCase("elroy"))
                .forEach(System.out::println);
    }

}
