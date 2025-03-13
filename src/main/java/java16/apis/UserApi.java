package java16.apis;

import java16.dto.request.UpdateUserDTO;
import java16.dto.response.ProfileDTO;
import java16.dto.response.SimpleResponseLogin;
import java16.dto.response.UserDTO;
import java16.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApi {

    private final UserService userService;


    @GetMapping("/getUserById/{userId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public UserDTO findByUser(@PathVariable Long userId) {
        return userService.findByUserId(userId);

    }

    @PreAuthorize("hasAuthority('USER')")
        @PutMapping("/updateUser/{userId}")
    public SimpleResponseLogin updateUser(@PathVariable Long userId , @RequestBody UpdateUserDTO updateUserDTO){
        return userService.updateUserId(userId,updateUserDTO);
        }

        @PreAuthorize("hasAuthority('USER')")
        @GetMapping("/profile/{userId}")
    public ProfileDTO profile(@PathVariable Long userId){
        return userService.profile(userId);
        }


}
