package java16.service;

import java16.dto.request.LoginDTO;
import java16.dto.request.RegisterDTO;
import java16.dto.response.SimpleResponse;
import java16.entitys.User;

import java.util.Optional;


public interface UserService {


    SimpleResponse userRegister(RegisterDTO registerDTO);

    SimpleResponse  login(LoginDTO loginDTO);


//     Optional<User>  findBymail(String userName);
}
