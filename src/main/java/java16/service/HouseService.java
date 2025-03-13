package java16.service;

import java16.dto.request.AddHouseDTO;
import java16.dto.request.GetAllHouseDTO;
import java16.dto.request.UpdateHouseDTO;
import java16.dto.response.SimpleResponse;
import java16.entitys.House;
import java16.entitys.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HouseService {
    SimpleResponse addHouse(Long userId,AddHouseDTO addHouseDTO);

    SimpleResponse getAll();





    SimpleResponse findById(Long houseId);

    SimpleResponse deleteHouse(Long houseId);

    SimpleResponse updateHouse(Long houseId, UpdateHouseDTO updateHouseDTO);
}
