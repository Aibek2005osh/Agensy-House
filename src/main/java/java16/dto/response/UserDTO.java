package java16.dto.response;

import java16.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDTO {
    private Long id;
    private String userName;
    private String email;
    private String imageURL;
    private Role  role;


}
