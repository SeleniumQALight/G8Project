package api.dto.responseDto;


import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorDTO {
    private String username;
    private String avatar;

//    public AuthorDTO(String username) {
//    }
}
