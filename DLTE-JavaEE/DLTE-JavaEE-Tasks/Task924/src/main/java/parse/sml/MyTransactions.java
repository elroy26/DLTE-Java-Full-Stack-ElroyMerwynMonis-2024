package parse.sml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="transactions")
public class MyTransactions {
    private List<Transaction> transactions;

    public MyTransactions() {
    }

    public MyTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
    @XmlElement(name="transaction")
    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
