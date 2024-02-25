package apiBooks.dto.requestDTO;


import lombok.*;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AddBookToUserDTO {
    private String userId;
    private ArrayList<CollectionDTO> collectionOfIsbns;

}
