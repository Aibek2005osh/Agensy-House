package java16.dto.response;


import java16.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data @Builder
public class AuthResponse {

    private String token;
    private String email;
    private Role role;
}
