package api.dto.responseDto;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CreatePostDto {
    private String title;
    private String body;
    private String select1;
    private String uniquePost;
    private String token;
}
