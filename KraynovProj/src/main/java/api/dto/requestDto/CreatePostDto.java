package api.dto.requestDto;


import lombok.*;

@Data
@Builder
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class CreatePostDto {

    private String title;
    private String body;
    private String select1;
    private String uniquePost;
    private String token;
}
