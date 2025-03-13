package java16.service.impl;

import jakarta.annotation.PostConstruct;

import jakarta.transaction.Transactional;
import java16.config.jwt.JwtService;
import java16.dto.request.GetAllHouseDTO;
import java16.dto.request.UpdateUserDTO;
import java16.dto.response.ProfileDTO;
import java16.dto.response.UserDTO;
import java16.dto.request.LoginDTO;
import java16.dto.request.RegisterDTO;
import java16.dto.response.SimpleResponse;
import java16.dto.response.SimpleResponseLogin;
import java16.entitys.House;
import java16.entitys.User;
import java16.enums.Role;
import java16.repo.UserRepo;
import java16.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class    UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

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

            User saveUser = userRepo.save(user);
            String generateToken = jwtService.generateToken(saveUser);




            return SimpleResponse.
                    builder()
                    .message("succes")
                    .status(HttpStatus.CREATED)
                    .data(generateToken)
                    .build();

        } catch (RuntimeException e) {
          return SimpleResponse.builder()
                  .message("not found ")
                  .status(HttpStatus.NOT_FOUND)

                  .build();
        }
    }

    @Override
    public SimpleResponse login(LoginDTO loginDTO) {

        try {
            User findUser = userRepo.findByEmail(loginDTO.email())
                    .orElseThrow(() -> new RuntimeException("Email табылган жок"));
            String genToken = jwtService.generateToken(findUser);
            if (passwordEncoder.matches(loginDTO.password(), findUser.getPassword())) {
                return SimpleResponse.builder()
                        .message("Ийгиликтүү кирди")
                        .status(HttpStatus.OK)
                        .data(genToken)
                        .build();

            }
            return SimpleResponse.builder()
                    .message("Жараксыз email же сырсөз")
                    .status(HttpStatus.UNAUTHORIZED)
                    .build();
        } catch (Exception e) {
            return SimpleResponse.builder()
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
        userDto.setPhoneNumber(foundUser.getPhoneNumber());

        return userDto;

    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }


    @Override
    public SimpleResponseLogin updateUserId(Long userId, UpdateUserDTO updateUserDTO) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("not found ID : " + userId));


      user.setUserName(updateUserDTO.getUserName());
      user.setEmail(updateUserDTO.getEmail());
      user.setPassword(passwordEncoder.encode(updateUserDTO.getPassword()));
      user.setPhoneNumber(updateUserDTO.getPhoneNumber());
      user.setImageURL(updateUserDTO.getImageURL());


        try {
            userRepo.save(user);
            return SimpleResponseLogin.builder()
                    .message("success updatet")
                    .status(HttpStatus.CREATED)
                    .build();


        } catch (Exception e) {
            return SimpleResponseLogin.builder()
                    .message("error"+e.getMessage())
                    .status(HttpStatus.NO_CONTENT)
                    .build();

        }
    }

    @Override
    @Transactional
    public ProfileDTO profile(Long userId) {

        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("not found  ID : " + userId));
        List<GetAllHouseDTO> houseDTOs = new ArrayList<>();
        List<House> house = user.getHouse();
        System.err.println(house);
        for (House house1 : house) {
           houseDTOs.add(new GetAllHouseDTO(house1.getId(),List.of("http://"), house1.getDescription(), house1.getPrice(),house1.getAddress()));
        }


        houseDTOs.add(new GetAllHouseDTO(100L,List.of("sdf"),"dasdf", BigDecimal.valueOf(20.00),"asdf"));

        // ProfileDTO түзүү
        return ProfileDTO.builder()

                .userName(user.getUsername())

                .email(user.getEmail())

                .houses(houseDTOs)
                .build();

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
