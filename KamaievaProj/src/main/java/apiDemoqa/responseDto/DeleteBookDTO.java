package apiDemoqa.responseDto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeleteBookDTO {
    public String isbn;
    public String userId;

}
