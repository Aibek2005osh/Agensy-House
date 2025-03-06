package java16.apis;

import java16.dto.request.AddHouseDTO;
import java16.dto.response.SimpleResponse;
import java16.entitys.House;
import java16.service.HouseService;
import java16.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserApi {

    private final UserService userService;




}
