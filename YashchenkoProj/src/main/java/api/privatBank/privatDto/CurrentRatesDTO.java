package api.privatBank.privatDto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CurrentRatesDTO {
    private String ccy;
    private String base_ccy;
    private String buy;
    private String sale;
}
