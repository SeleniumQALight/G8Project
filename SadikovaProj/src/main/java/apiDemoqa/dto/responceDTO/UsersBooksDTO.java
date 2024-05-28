package apiDemoqa.dto.responceDTO;

import lombok.*;

import java.util.ArrayList;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsersBooksDTO {
    private String userId;
    private String username;
    private ArrayList<BookDTO> books;
}