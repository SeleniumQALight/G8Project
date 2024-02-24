package api.dto.requestBookDto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class DeleteBookDTO {
    private String userId;
    private String isbn;
}