package apiDemoqa.dto.requestDTO;

import apiDemoqa.dto.responceDTO.BookDTO;
import lombok.*;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BookStoreDTO {
    private List<BookDTO> books;
    public List<BookDTO> getBooks() {
        return books;
    }

}