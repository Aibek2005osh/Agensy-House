package java16.apis;

import jakarta.annotation.security.PermitAll;
import java16.dto.request.AddHouseDTO;
import java16.dto.request.GetAllHouseDTO;
import java16.dto.response.SimpleResponse;
import java16.service.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/house")
@RequiredArgsConstructor
public class HouseApi {

    private final HouseService houseService;



    @PostMapping("/addHouse")
    public SimpleResponse add(@RequestBody AddHouseDTO addHouseDTO) {
        return houseService.addHouse(addHouseDTO);
    }




    @GetMapping("/getAllHouses")
    public SimpleResponse getAll(@RequestBody GetAllHouseDTO getAllHouse) {
        return houseService.getAll(getAllHouse);
    }


}
