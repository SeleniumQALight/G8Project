package apiDemoqa.dto.responseDto;

import lombok.*;
import java.util.ArrayList;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsersBooksDto {

    private String userId;
    private String username;
    private ArrayList<BookDto> books;

}