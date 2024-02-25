package api.privatBank.privatDto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ExchangeRateDTO {
    private String baseCurrency;
    private String currency;
    private Double saleRateNB;
    private Double purchaseRateNB;
    private Double saleRate;
    private Double purchaseRate;

    public ExchangeRateDTO(String currency) {
        this.baseCurrency = "UAH";
        this.currency = currency;
    }
}
