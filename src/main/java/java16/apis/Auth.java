package java16.apis;

import java16.dto.request.LoginDTO;
import java16.dto.request.RegisterDTO;

import java16.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class Auth {
    private final UserService userService;

    @PostMapping("/register")
    public void register(@RequestBody RegisterDTO registerDTO) {
        userService.userRegister(registerDTO);
    }

    @GetMapping("/login")
    public boolean login(LoginDTO loginDTO) {

        try {
            userService.login(loginDTO);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);

        }


    }

}
