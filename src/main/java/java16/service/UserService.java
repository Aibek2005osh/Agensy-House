package java16.service;

import java16.dto.request.LoginDTO;
import java16.dto.request.RegisterDTO;
import java16.dto.response.SimpleResponse;

public interface UserService {


    SimpleResponse userRegister(RegisterDTO registerDTO);

    boolean  login(LoginDTO loginDTO);
}
