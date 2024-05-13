package api.privat24.responseDTO;

import lombok.*;


@Data
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
