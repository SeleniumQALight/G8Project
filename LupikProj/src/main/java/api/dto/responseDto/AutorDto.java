package api.dto.responseDto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class AutorDto {

    private String username;
    private String avatar;






    public AutorDto(String username) {
        this.username = username;
    }


}
