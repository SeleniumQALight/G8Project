package apiPrivatBank.PBDto;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExchangeRatesByCourseDTO {

    private String ccy;
    private String base_ccy;
    private String buy;
    private String sale;

}
