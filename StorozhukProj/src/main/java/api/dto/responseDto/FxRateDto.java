package api.dto.responseDto;

import lombok.*;
@Data
@ToString
@NoArgsConstructor
public class FxRateDto {
    private String ccy;
    private String base_ccy;
    private Double buy;
    private Double sale;
}
