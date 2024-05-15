package apiPrivatBank.responsePrivatBankDto;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExchangeRateDto {
    private String baseCurrency;
    private String currency;
    private String saleRateNB;
    private String purchaseRateNB;
    private String saleRate;
    private String purchaseRate;

    public static ExchangeRateDto createExchangeRateWithSpecificCurrency(String currency) {
        return ExchangeRateDto.builder().baseCurrency("UAH").currency(currency).build();
    }

}