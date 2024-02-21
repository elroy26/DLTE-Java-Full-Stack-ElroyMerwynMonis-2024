package store.oops;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BankBonds {

    private Double maturity;
    private Double interestRate;
    private Boolean taxStatus;
    private String bondHolder;
    private Integer period;

}
