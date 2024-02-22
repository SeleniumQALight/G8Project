package apiBooks.dto.responsetDTO;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsersBooksDTO {
    private String userId;
    private String username;
    private ArrayList<BooksDTO> books;

}
