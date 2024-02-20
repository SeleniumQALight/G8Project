package apiPrivatbank.dto.responseDto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyDTO {

    private String date;
    private String bank;
    private Integer baseCurrency;
    private String baseCurrencyLit;
    private ExchangeRatesDTO[] exchangeRate;

}
