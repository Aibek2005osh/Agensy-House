package java16.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {



    private String userName;

    private String email;

    private String password;

    private String phoneNumber;

    private String imageUrl;
}


