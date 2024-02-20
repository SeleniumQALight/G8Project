package apiPrivatbank.dto.responseDto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExchangeRatesDTO {

    private String baseCurrency;
    private String currency;
    private Double saleRateNB;
    private Double purchaseRateNB;
    private Double saleRate;
    private Double purchaseRate;

}
