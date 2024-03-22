package apiPrivatBank.responsePrivatBankDto;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyRateDto {

    private String ccy;
    private String base_ccy;
    private String buy;
    private String sale;
}