package api.dto.responseDto;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
public class AuthorDto {
    private String username;
    private String avatar;

    public AuthorDto(String username) {
        this.username = username;
    }
}
