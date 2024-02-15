package api.dto.responseDto;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class AuthorDTO {
    private String username;
    private String avatar;

    public AuthorDTO(String username) {
        this.username = username;
    }
}
