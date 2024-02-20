package api.dto.privat;

import lombok.*;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExchangeDTO {
    private String date;
    private String bank;
    private String baseCurrency;
    private String baseCurrencyLit;
    private ExchangeRateDTO[] exchangeRate;
}
