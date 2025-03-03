package java16.service.impl;

import jakarta.persistence.PrePersist;
import java16.dto.request.LoginDTO;
import java16.dto.request.RegisterDTO;
import java16.dto.response.SimpleResponse;
import java16.entitys.User;
import java16.enums.Role;
import java16.repo.UserRepo;
import java16.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;


    @Override
    public SimpleResponse userRegister(RegisterDTO registerDTO) {

        User user = new User();
        if (userRepo.existsByEmail(registerDTO.getEmail())) {
            return new SimpleResponse("Бул email мурун катталган!", HttpStatus.BAD_REQUEST);
        }
        if (userRepo.existsByUserName(registerDTO.getUserName())) {
            return new SimpleResponse("Бул колдонуучу аты колдонулуп жатат!", HttpStatus.BAD_REQUEST);
        }
        user.setUserName(registerDTO.getUserName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        user.setPhoneNumber(registerDTO.getPhoneNumber());
        try {
            userRepo.save(user);
        } catch (Exception e) {
            return SimpleResponse.builder()
                    .message(e.getMessage())
                    .status(HttpStatus.CONFLICT)
                    .build();
        }
        return SimpleResponse.builder()
                .message("User successfully saved")
                .status(HttpStatus.CREATED)
                .build();
    }

    @Override
    public boolean login(LoginDTO loginDTO) {

        User findUser = userRepo.findByEmail((loginDTO.email()));
        if (findUser == null) {
            return false;
        }


        return findUser != null && loginDTO.password().equals(findUser.getPassword());


    }

    @PrePersist
    private void init() {
        User user = new User();
        user.setUserName("admin");
        user.setPassword("admin");
        user.setEmail("admin@admin.com");
        user.setPhoneNumber("010-1111-2222");
        user.setRole(Role.ADMIN);
        userRepo.save(user);


    }
}
