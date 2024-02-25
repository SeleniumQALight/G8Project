package api.dto.demoQa.responseDto;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LoginDto {
    private String userId;
    private String username;
    private String password;
    private String token;
    private Boolean isActive;
    private Date created_date;
    private Date expires;
}
