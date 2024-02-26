package api.dto.responseBookDto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class LoginDTO {
        private String userId;
        private String username;
        private String password;
        private String token;
        private Boolean isActive;
        private Date expires;
        private Date created_date;
}