package apiBooksStore.requestDTO;


import lombok.*;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AddBookDTO {

    private String userId;
    private ArrayList<CollectionDTO> collectionOfIsbns;

}
