package api.demoQA.requestDTO;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDto {
    private String userName;
    private String password;
}
