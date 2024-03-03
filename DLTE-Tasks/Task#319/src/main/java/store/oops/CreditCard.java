package store.oops;

import lombok.*;

import java.util.Date;

@Getter
@Data
@AllArgsConstructor
@Setter
@NoArgsConstructor
@ToString
public class CreditCard {
    //dateOfTransaction, amountInTransaction, to, remarks(Family, Education, Emergency, Bills, Friend)
    private Date dateOfTransaction;
    private Double amountInTransaction;
    private String toRecipient;
    private String remarks;
}
