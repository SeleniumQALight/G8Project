package apiPrivat.responsePrivatBankDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExchangeRateDTO {
    private String baseCurrency;
    private String currency;
    private Double saleRateNB;
    private Double purchaseRateNB;
    private Double saleRate;
    private Double purchaseRate;

    public static ExchangeRateDTO createExchangeRateWithDefCurrency(String currency){
        return ExchangeRateDTO.builder().baseCurrency("UAH").currency(currency).build();


    }
}
