package apiBooksStore.responsetDTO;


import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsersDTO {

    private String username;
    private String userId;
    private String email;
    private ArrayList<String> books;
}
