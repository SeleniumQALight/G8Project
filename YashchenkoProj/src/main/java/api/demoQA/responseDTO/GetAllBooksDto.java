package api.demoQA.responseDTO;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetAllBooksDto {
    BooksDto[] books;
}
