package api.dto.demoQa.requestDto;

import lombok.*;

import java.util.ArrayList;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AddBookToUserDto {
    private String userId;
    private ArrayList<CollectionDto> collectionOfIsbns;

}
