package java16.service;

import java16.dto.request.AddHouseDTO;
import java16.dto.request.GetAllHouseDTO;
import java16.dto.response.SimpleResponse;
import java16.entitys.House;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HouseService {
    SimpleResponse addHouse(AddHouseDTO addHouseDTO);

    SimpleResponse getAll();


    List<House> getAllHouses();
}
