package api.dto.responseBookDto;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserBooksDTO {
        private String userId;
        private String username;
        private ArrayList<AllBooksDTO> books;
}