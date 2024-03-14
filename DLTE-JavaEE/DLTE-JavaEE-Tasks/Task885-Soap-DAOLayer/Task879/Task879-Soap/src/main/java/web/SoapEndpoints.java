package web;

import javax.xml.ws.Endpoint;

public class SoapEndpoints {
    private static String url="http://localhost:1234/wealthWarriors";

    public static void main(String[] args) {
        TransactionsSoap transactionsSoap=new TransactionsSoap();
        System.out.println("Webservice hosted @ "+url);
        Endpoint.publish(url,transactionsSoap);

    }
}
