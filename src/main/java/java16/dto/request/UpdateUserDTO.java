package java16.dto.request;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter  @NoArgsConstructor
public class UpdateUserDTO {


    private Long id;

    private String userName;

    private String email;

    private String password;

    private String phoneNumber;

    private String imageURL;

}
