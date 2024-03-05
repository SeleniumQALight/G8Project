package apiDemoqa.responseDTO;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDTO {
    private String userId;
    private String username;
    private String password;
    private String token;
    private Date expires;
    private Date created_date;
    private Boolean isActive;


}
