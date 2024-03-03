package api.dto.requestDto;


import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AddBookDemoqaDto {
    private String userId;
    private List<CollectionOfIsbnDemoqaDto> collectionOfIsbns;

}
