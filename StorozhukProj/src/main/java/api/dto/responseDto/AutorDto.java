package api.dto.responseDto;


import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AutorDto {
    private String username;
    private String avatar;

//    public AutorDto(){
//    }

//    public AutorDto(String username) {
//        this.username = username;
//    }

//    public String getUsername() {
//        return username;
//    }
//
//    public String getAvatar() {
//        return avatar;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public void setAvatar(String avatar) {
//        this.avatar = avatar;
//    }

//    @Override
//    public String toString() {
//        return "AutorDto{" +
//                "username='" + username + '\'' +
//                ", avatar='" + avatar + '\'' +
//                '}';
//    }
}
