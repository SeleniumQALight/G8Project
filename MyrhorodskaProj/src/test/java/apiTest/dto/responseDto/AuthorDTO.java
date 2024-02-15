package apiTest.dto.responseDto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class AuthorDTO {
    private String username;
    private String avatar;



    public void setUsername(String username) {
        this.username = username;
    }


    public AuthorDTO(String username) {
        this.username = username; //for expected result
    }

}
