package api.dto.responseDto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CurrencyRateListPbDto {
    List<CurrencyRatePbDto> currencyRateList;
}
