package apiPrivatbank.dto.responseDto;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
public class ExchangeRateByIdDTO {
    private String ccy;
    private String base_ccy;
    private String buy;
    private String sale;
}
