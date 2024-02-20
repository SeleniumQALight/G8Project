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



  //  public void setUsername(String username) {
    //    this.username = username;
    //}


    //public AuthorDTO(String username) {
     //   this.username = username; //for expected result
    //}

}
