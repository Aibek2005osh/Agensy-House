package java16.apis;

import jakarta.annotation.security.PermitAll;
import java16.dto.request.AddHouseDTO;
import java16.dto.request.GetAllHouseDTO;
import java16.dto.request.UpdateHouseDTO;
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
   @PostMapping("/addHouse/{userId}")
    public SimpleResponse add(@PathVariable Long userId  ,  @RequestBody AddHouseDTO addHouseDTO) {
        return houseService.addHouse(userId,addHouseDTO);

    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping("/getAllHouses")
    public SimpleResponse getAll() {
        return houseService.getAll();
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("findByHouseId/{houseId}")
    public SimpleResponse findById(@PathVariable Long houseId) {
        return houseService.findById(houseId);
    }

    @PreAuthorize("hasAuthority('USER')")
    @DeleteMapping("/deletHouse/{houseId}")
    public SimpleResponse deleteHouse(@PathVariable Long houseId) {
        return houseService.deleteHouse(houseId);
    }

    @PreAuthorize("hasAuthority('USER')")
    @PutMapping("/updateHouse/{houseId}")
    public SimpleResponse updateHouse(@PathVariable Long houseId, @RequestBody UpdateHouseDTO updateHouseDTO) {
        return houseService.updateHouse(houseId, updateHouseDTO);
    }


}
