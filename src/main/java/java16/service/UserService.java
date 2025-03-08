package java16.service;

import java16.dto.response.UserDTO;
import java16.dto.request.LoginDTO;
import java16.dto.request.RegisterDTO;
import java16.dto.response.SimpleResponse;
import java16.dto.response.SimpleResponseLogin;
import java16.entitys.User;

import java.util.List;


public interface UserService {


    SimpleResponse userRegister(RegisterDTO registerDTO);

    SimpleResponseLogin login(LoginDTO loginDTO);

    UserDTO findByUserId(Long userId);

    List<User> getAllUsers();

//     Optional<User>  findBymail(String userName);



}
