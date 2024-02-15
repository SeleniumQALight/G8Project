package api.dto.responseDto;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
public class AuthorDTO {
    private String username;
    public String avatar;

      public AuthorDTO(String username) {
        this.username = username;
    }
}
