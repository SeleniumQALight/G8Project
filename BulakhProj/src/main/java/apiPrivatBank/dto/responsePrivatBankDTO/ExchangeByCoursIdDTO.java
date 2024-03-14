package apiPrivatBank.dto.responsePrivatBankDTO;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExchangeByCoursIdDTO {
    private String ccy;
    private String base_ccy;
    private String buy;
    private String sale;

}
