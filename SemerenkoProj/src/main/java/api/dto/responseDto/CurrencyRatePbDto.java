package api.dto.responseDto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CurrencyRatePbDto {
    private String ccy;
    private String base_ccy;
    private Double buy;
    private Double sale;
}
