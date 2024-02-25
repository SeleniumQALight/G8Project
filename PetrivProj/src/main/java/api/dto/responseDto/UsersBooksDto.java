package api.dto.responseDto;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsersBooksDto {
    private String userId;
    private String username;
    private BookDetailsDto[] books;
}
