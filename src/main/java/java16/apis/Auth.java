package java16.apis;
import java16.dto.request.LoginDTO;
import java16.dto.request.RegisterDTO;

import java16.dto.response.SimpleResponse;
import java16.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class Auth {
    private final UserService userService;



    @PostMapping("/register")
    public ResponseEntity<SimpleResponse> register(@RequestBody RegisterDTO registerDTO) {
        return ResponseEntity.ok(userService.userRegister(registerDTO));
    }



    @PostMapping("/login")
    public SimpleResponse login(@RequestBody LoginDTO loginDTO) {

        return   userService.login(loginDTO);



    }

}
