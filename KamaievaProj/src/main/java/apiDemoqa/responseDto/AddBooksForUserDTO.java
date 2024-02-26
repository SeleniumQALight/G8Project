package apiDemoqa.responseDto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddBooksForUserDTO {
    private String userId;
    private List<IsbnDTO> collectionOfIsbns;

}
