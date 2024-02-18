package api_privat.responseDto;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExchangeRateDTO {
    private String baseCurrency;
    private String currency;
    private String saleRateNB;
    private String purchaseRateNB;
    private String saleRate;
    private String purchaseRate;
}
