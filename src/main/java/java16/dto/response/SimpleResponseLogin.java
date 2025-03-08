package java16.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@Builder @Getter
public class SimpleResponseLogin {

    private String message;
    private HttpStatus status;
}
