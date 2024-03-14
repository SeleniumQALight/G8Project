package api.dto.responseDto;
import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyRateDto {
    private String ccy;
    private String base_ccy;
    private Double buy;
    private Double sale;
}
