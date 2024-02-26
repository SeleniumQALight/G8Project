package api.dto.demoQa.requestDto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DeleteBookDto {
    private String userId;
    private String isbn;
}
