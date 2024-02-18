package api.dto.responseDto;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExchangeRatePbDto {
    private String baseCurrency;
    private String currency;
    private double saleRateNB;
    private double purchaseRateNB;
    private double saleRate;
    private double purchaseRate;
}
