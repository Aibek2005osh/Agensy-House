package java16.service;

import java16.dto.request.AddHouseDTO;
import java16.dto.request.GetAllHouseDTO;
import java16.dto.response.SimpleResponse;
import org.springframework.stereotype.Service;

@Service
public interface HouseService {
    SimpleResponse addHouse(AddHouseDTO addHouseDTO);

    SimpleResponse getAll(GetAllHouseDTO getAllHouseDTO);
}
