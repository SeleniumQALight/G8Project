package api.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthorDto {
    private String username;
    private String avatar;

    public AuthorDto(String username) {
        this.username = username;
    }
}
