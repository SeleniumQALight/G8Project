package apiBooksStore.requestDTO;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DeleteBookDTO {

        private String userId;
        private String isbn;
}
