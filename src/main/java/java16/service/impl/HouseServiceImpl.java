package java16.service.impl;

import java16.dto.request.AddHouseDTO;
import java16.dto.request.GetAllHouseDTO;
import java16.dto.request.UpdateHouseDTO;
import java16.dto.response.SimpleResponse;
import java16.dto.response.SimpleResponseLogin;
import java16.entitys.House;
import java16.entitys.Image;
import java16.entitys.User;
import java16.repo.HouseRepo;
import java16.repo.ImageRepo;
import java16.repo.UserRepo;
import java16.service.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {


    private final HouseRepo houserepo;

    private final ImageRepo imageRepo;

    private final UserRepo userRepo;


    @Override
    public SimpleResponse addHouse(Long userId, AddHouseDTO addHouseDTO) {
        House house = new House();
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("not found user ID : " + userId));

        house.setOwner(user);

        house.setHomeType(addHouseDTO.getHomeType());
        house.setTitle(addHouseDTO.getTitle());
        house.setDescription(addHouseDTO.getDescription());
        house.setPrice(addHouseDTO.getPrice());
        house.setMaxOfGuests(addHouseDTO.getMaxOfGuests());
        house.setRegion(addHouseDTO.getRegion());
        house.setAddress(addHouseDTO.getAddress());

        List<Image> images = new ArrayList<>();
        for (String imageUrl : addHouseDTO.getImages()) {
            Image image = new Image();
            image.setImage(imageUrl);
            image.setHouse(house);
            images.add(image);
        }

        house.setImages(images);

        try {

            houserepo.save(house);
            imageRepo.saveAll(images);
            return new SimpleResponse("Successfully added", HttpStatus.CREATED);

        } catch (Exception e) {

            return new SimpleResponse("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @Override
    public SimpleResponse getAll() {
        List<GetAllHouseDTO> getAllHouseDTO = new ArrayList<>();

        try {
            List<House> houses = houserepo.findAll();
            if (houses.isEmpty()) {
                return SimpleResponse.builder()
                        .message("No houses found")
                        .status(HttpStatus.NOT_FOUND)
                        .build();
            }

            for (House house : houses) {
                List<String> imageUrls = house.getImages().stream()
                        .map(Image::getImage)
                        .toList();

                GetAllHouseDTO dto = GetAllHouseDTO.builder()
                        .id(house.getId())
                        .images(imageUrls)
                        .description(house.getDescription())
                        .price(house.getPrice())
                        .address(house.getAddress())
                        .build();

                getAllHouseDTO.add(dto);
            }

            return SimpleResponse.builder()
                    .message("Houses retrieved successfully")
                    .status(HttpStatus.OK)
                    .data(getAllHouseDTO)
                    .build();

        } catch (Exception e) {
            return SimpleResponse.builder()
                    .message("Error: " + e.getMessage())
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();

        }

    }

    @Override
    public SimpleResponse findById(Long houseId) {
        House byUserId = houserepo.findByHouseId(houseId);
        try {

            return SimpleResponse
                    .builder()
                    .message("ok")
                    .data(byUserId)
                    .status(HttpStatus.OK)
                    .build();

        } catch (RuntimeException e) {
            return SimpleResponse.builder()
                    .message("Error: " + e.getMessage())
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @Override
    public SimpleResponse deleteHouse(Long houseId) {

        House byHouseId = houserepo.findByHouseId(houseId);
        try {
            houserepo.delete(byHouseId);
            return SimpleResponse
                    .builder()
                    .status(HttpStatus.OK)
                    .message("success delete 👌👌")
                    .data(byHouseId)
                    .build();
        } catch (Exception e) {
            return SimpleResponse.builder()
                    .status(HttpStatus.CONFLICT)
                    .message("error" + e.getMessage())
                    .data(byHouseId)
                    .build();
        }
    }

    @Override
    public SimpleResponse updateHouse(Long houseId, UpdateHouseDTO updateHouseDTO) {


            House byHouseId = houserepo.findByHouseId(houseId);

        List<Image> images = new ArrayList<>();
        for (String imageUrl : updateHouseDTO.getImages()) {
            Image image = new Image();
            image.setImage(imageUrl);
            image.setHouse(byHouseId);
            images.add(image);
        }
        byHouseId.setImages(images);
        byHouseId.setTitle(updateHouseDTO.getTitle());
        byHouseId.setDescription(updateHouseDTO.getDescription());
        byHouseId.setDescription(updateHouseDTO.getDescription());
        byHouseId.setMaxOfGuests(updateHouseDTO.getMaxOfGuests());

        try {
            houserepo.save(byHouseId);

            return SimpleResponse.builder()
                    .message("successfull udaate ")
                    .status(HttpStatus.OK)
                    .data(byHouseId)
                    .build();
        } catch (Exception e) {
            return SimpleResponse.builder()
                    .message("error udaate "+e.getMessage())
                    .status(HttpStatus.CONFLICT)
                    .data(byHouseId)
                    .build();
        }
    }
}
