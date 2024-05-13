package api.dto.responseDto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Data
@ToString
@NoArgsConstructor //генерирует конструктор
@AllArgsConstructor
@Builder
public class PostDto {
    @JsonProperty("_id")
    private String id;
    private String title;
    private String body;
    @JsonProperty("select1")
    private String select;
    private String uniquePost;
    private String createdDate;
    private AuthorDTO author;
    private Boolean isVisitorOwner;


//    public PostDto( String title, String body, String select, String uniquePost, AuthorDTO author, Boolean isVisitorOwner) {
//        this.title = title;
//        this.body = body;
//        this.select = select;
//        this.uniquePost = uniquePost;
//        this.author = author;
//        this.isVisitorOwner = isVisitorOwner;
//    }
}
