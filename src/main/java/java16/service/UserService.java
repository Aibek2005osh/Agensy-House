package java16.service;

import java16.dto.request.UpdateUserDTO;
import java16.dto.response.ProfileDTO;
import java16.dto.response.UserDTO;
import java16.dto.request.LoginDTO;
import java16.dto.request.RegisterDTO;
import java16.dto.response.SimpleResponse;
import java16.dto.response.SimpleResponseLogin;
import java16.entitys.User;

import java.util.List;


public interface UserService {


    SimpleResponse userRegister(RegisterDTO registerDTO);

    SimpleResponse login(LoginDTO loginDTO);

    UserDTO findByUserId(Long userId);

    List<User> getAllUsers();

    SimpleResponseLogin updateUserId(Long userId, UpdateUserDTO updateUserDTO);

    ProfileDTO profile(Long userId);



//     Optional<User>  findBymail(String userName);



}
