package apiDemoqa.dto.requestDto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DeleteBookDto {

    private String userId;
    private String isbn;

}