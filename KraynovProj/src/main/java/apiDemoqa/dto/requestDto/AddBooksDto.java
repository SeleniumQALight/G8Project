package apiDemoqa.dto.requestDto;

import lombok.*;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AddBooksDto {

    private String userId;
    private ArrayList<CollectionOfIsbnsDto> collectionOfIsbns;

}
