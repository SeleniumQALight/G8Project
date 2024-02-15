package api.dto.responseDto;


import groovy.transform.ToString;
import lombok.Data;
import lombok.NoArgsConstructor;

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
