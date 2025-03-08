package java16.apis;

import jakarta.annotation.security.PermitAll;
import java16.dto.request.AddHouseDTO;
import java16.dto.request.GetAllHouseDTO;
import java16.dto.response.SimpleResponse;
import java16.service.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/house")
@RequiredArgsConstructor
public class HouseApi {

    private final HouseService houseService;

    @PreAuthorize("hasAuthority('USER')")
   @PostMapping("/addHouse")
    public SimpleResponse add(@RequestBody AddHouseDTO addHouseDTO) {

        return houseService.addHouse(addHouseDTO);

    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping("/getAllHouses")
    public SimpleResponse getAll() {
        return
                houseService.getAll();
    }

}
