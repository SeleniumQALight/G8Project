package api_privat.responseDto;
import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExchangeRateMainDto {
    private String ccy;
    private String base_ccy;
    private float buy;
    private float sale;
}
