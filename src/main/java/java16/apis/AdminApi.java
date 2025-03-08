package java16.apis;

import java16.entitys.House;
import java16.entitys.User;
import java16.service.AdminService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@PreAuthorize("houseService('ADMIN')")
public class AdminApi {

//    private final UserService userService;
//
//    private final HouseService houseService;

    private final AdminService adminService;

    @PreAuthorize("houseService('ADMIN')")
    @GetMapping("/users")
    public List<User> getAllUsers(){
      return   adminService.getAllUsers();
    }

    @PreAuthorize("houseService('ADMIN')")
    @GetMapping("/houses")
    public List<House> getAllHoses(){
      return   adminService.getAllHouses();

    }

}
