package api.dto.responseDto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data // одна анотація замість @Getter + @Setter
@ToString
@NoArgsConstructor
public class AuthorDto {
    private String username;
    private String avatar;

    public AuthorDto(String username) {
        this.username = username;
    }
}
