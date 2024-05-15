package api.dto.requestDto;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CollectionOfIsbnsDto {
    private String isbn;
}