package api.dto.responseDto;

import lombok.*;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RateArchPbDto {
    private String date;
    private String bank;
    private int baseCurrency;
    private String baseCurrencyLit;
    private List<ExchangeRatePbDto> exchangeRate;


}
