package apiPrivatBank.responsePrivatBankDto;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExchangeDto {
    private String date;
    private String bank;
    private Integer baseCurrency;
    private String baseCurrencyLit;
    private ExchangeRateDto[] exchangeRate;

}