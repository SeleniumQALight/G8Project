package api.dto.responsePrivat;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class MainExchangeDataDto {
    private String date;
    private String bank;
    private int baseCurrency;
    private String baseCurrencyLit;
    private ExchangeRateDto[] exchangeRate;
}