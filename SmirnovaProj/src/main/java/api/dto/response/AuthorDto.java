package api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorDto {
    private String username;
    private String avatar;

//    public AuthorDto(String username) {
//        this.username = username;
//    }
//
 }
