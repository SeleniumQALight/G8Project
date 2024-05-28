package apiDemoqa.dto.requestDTO;

import apiDemoqa.dto.responceDTO.BookDTO;
import lombok.*;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AddBooksDTO {

    private String userId;
    private ArrayList<BookDTO> collectionOfIsbns;

}