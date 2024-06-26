package api.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class CreatePostDto {
    private String title;
    private String body;
    private String select1;
    private String uniquePost;
    private String token;
}
