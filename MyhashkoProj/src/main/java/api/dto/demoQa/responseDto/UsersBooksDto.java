package api.dto.demoQa.responseDto;

import lombok.*;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UsersBooksDto {
    private String userId;
    private String username;
    private ArrayList<BooksDto> books;
}
