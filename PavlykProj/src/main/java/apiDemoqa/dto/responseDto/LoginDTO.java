package apiDemoqa.dto.responseDto;

import lombok.*;

import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDTO {
    private String userId;
    private String username;
    private String password;
    private String token;
    private Boolean isActive;
    private Date created_date;
    private Date expires;
}