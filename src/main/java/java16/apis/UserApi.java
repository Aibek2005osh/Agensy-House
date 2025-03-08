package java16.apis;

import java16.dto.response.UserDTO;
import java16.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserApi {

    private final UserService userService;


    @GetMapping("/getUserById/{userId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public UserDTO findByUser(@PathVariable Long userId) {
        return userService.findByUserId(userId);

    }

}
