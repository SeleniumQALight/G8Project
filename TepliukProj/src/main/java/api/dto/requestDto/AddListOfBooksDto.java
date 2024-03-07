package api.dto.requestDto;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddListOfBooksDto {
    private String userId;
    private CollectionOfIsbnsDto[] collectionOfIsbns;
}