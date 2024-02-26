package api.dto.responseBookDto;

import api.dto.requestBookDto.IsbnDTO;
import lombok.*;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class AddBooksForUserDTO {
    private String userId;
    private ArrayList<IsbnDTO> collectionOfIsbns;
}