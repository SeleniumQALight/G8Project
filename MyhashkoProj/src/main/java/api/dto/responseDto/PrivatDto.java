package api.dto.responseDto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class PrivatDto {
    String ccy;
    String base_ccy;
    String buy;
    String sale;
}
