package java16.dto.response;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter @Setter @Builder @NoArgsConstructor
@AllArgsConstructor
public class SimpleResponse {

    private String message;
    private HttpStatus status;
    private Object data;

    public SimpleResponse(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
