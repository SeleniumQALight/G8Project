package apiDemoqa.dto.responseDto;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BookDTO {
    private String isbn;
    private String title;
    private String subTitle;
    private String author;
    private String publish_date;
    private String publisher;
    private String pages;
    private String description;
    private String website;
}