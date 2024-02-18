package api_privat.responseDto;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExchangeDto {
    private String date;
    private String bank;
    private String baseCurrency;
    private String baseCurrencyLit;
    private ExchangeRateDTO[] exchangeRate;
}
