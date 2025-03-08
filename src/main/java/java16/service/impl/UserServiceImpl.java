package java16.service.impl;

import jakarta.annotation.PostConstruct;

import java16.dto.response.UserDTO;
import java16.dto.request.LoginDTO;
import java16.dto.request.RegisterDTO;
import java16.dto.response.SimpleResponse;
import java16.dto.response.SimpleResponseLogin;
import java16.entitys.User;
import java16.enums.Role;
import java16.repo.UserRepo;
import java16.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class    UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder; // Кошуңуз

    @Override
    public SimpleResponse userRegister(RegisterDTO registerDTO) {

        User user = new User();
        if (userRepo.existsByEmail(registerDTO.getEmail())) {
            return new SimpleResponse("Бул email мурун катталган!", HttpStatus.BAD_REQUEST);
        }
        if (userRepo.existsByUserName(registerDTO.getUserName())) {
            return new SimpleResponse("Бул колдонуучу аты колдонулуп жатат!", HttpStatus.BAD_REQUEST);
        }

        try {

            user.setUserName(registerDTO.getUserName());
            user.setEmail(registerDTO.getEmail());
            String encode = passwordEncoder.encode(registerDTO.getPassword());// Шифрлөө
            user.setPassword(encode);
            user.setPhoneNumber(registerDTO.getPhoneNumber());
            user.setImageURL(registerDTO.getImageUrl());
            user.setRole(Role.USER);
            userRepo.save(user);

            return SimpleResponse.
                    builder()
                    .message("succes")
                    .status(HttpStatus.CREATED)
                    .build();

        } catch (RuntimeException e) {
          return SimpleResponse.builder()
                  .message("not found ")
                  .status(HttpStatus.NOT_FOUND)

                  .build();
        }
    }

    @Override
    public SimpleResponseLogin login(LoginDTO loginDTO) {
        try {
            User findUser = userRepo.findByEmail(loginDTO.email())
                    .orElseThrow(() -> new RuntimeException("Email табылган жок"));
            if (passwordEncoder.matches(loginDTO.password(), findUser.getPassword())) { // Текшерүү
                return SimpleResponseLogin.builder()
                        .message("Ийгиликтүү кирди")
                        .status(HttpStatus.OK)
                        .build();
            }
            return SimpleResponseLogin.builder()
                    .message("Жараксыз email же сырсөз")
                    .status(HttpStatus.UNAUTHORIZED)
                    .build();
        } catch (Exception e) {
            return SimpleResponseLogin.builder()
                    .message(e.getMessage())
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @Override
    public UserDTO findByUserId(Long userId) {

        User foundUser = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("user with id : " + userId + "not found"));

        UserDTO userDto = new UserDTO();
        userDto.setId(foundUser.getId());
        userDto.setUserName(foundUser.getUsername());
        userDto.setEmail(foundUser.getEmail());
        userDto.setImageURL(foundUser.getImageURL());
        userDto.setRole(foundUser.getRole());
        return userDto;

    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @PostConstruct
    private void init() {
        String adminEmail = "admin@gmail.com";


        if (!userRepo.existsByEmail(adminEmail)) {
            User user = new User();
            user.setUserName("Admin");
            user.setPassword(passwordEncoder.encode("ADMIN123"));
            user.setEmail(adminEmail);
            user.setPhoneNumber("+996700000000");
            user.setRole(Role.ADMIN);
            userRepo.save(user);

            log.info("Администратор аккаунту ийгиликтүү түзүлдү.");
        } else {
            log.info("Администратор аккаунту мурунтан эле бар.");
        }
    }

}
