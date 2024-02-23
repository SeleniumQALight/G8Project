package api.dto.responseDto;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BooksDto {
    private BookDetailsDto[] books;
}
