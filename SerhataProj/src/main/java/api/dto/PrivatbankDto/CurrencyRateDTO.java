package api.dto.PrivatbankDto;
import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyRateDTO {
    private String ccy;
    private String base_ccy;
    private Double buy;
    private Double sale;
}