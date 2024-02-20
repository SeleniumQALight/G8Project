package apiPrivatBank.PBDto;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExchangeDTO {
    private String baseCurrency;
    private String currency;
    private Integer saleRateNB;
    private Integer purchaseRateNB;
    private Integer saleRate;
    private Integer purchaseRate;
}