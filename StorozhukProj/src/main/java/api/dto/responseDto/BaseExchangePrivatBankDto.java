package api.dto.responseDto;

import lombok.*;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseExchangePrivatBankDto {

    private String date;
    private String bank;
    private Integer baseCurrency;
    private String baseCurrencyLit;
    private ExchangeRateDto[] exchangeRate;
}
