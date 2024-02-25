package api.privatBank.privatDto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ArchiveRatesDTO {
    private String date;
    private String bank;
    private Integer baseCurrency;
    private String baseCurrencyLit;
    private ExchangeRateDTO[] exchangeRate;
}
