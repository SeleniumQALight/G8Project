package api.dto.responseDto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserInfoDemoqaDto {
    private String userId;
    private String username;
    private List<BooksDemoqaDto> books;
}
