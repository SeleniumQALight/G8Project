package api.dto.responseDto;

import lombok.*;

@Data // одна анотація замість @Getter + @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorDto {
    private String username;
    private String avatar;

    /*public AuthorDto(String username) {
        this.username = username;
    }*/
}
