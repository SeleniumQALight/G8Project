package api.demoQA.requestDTO;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddBookForUserDto {
    private String userId;
    IsbnDTO[] collectionOfIsbns;
}
