package java16.dto.response;

import java16.dto.request.GetAllHouseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter @Getter @Builder
public class ProfileDTO {


    private String userName;
    private String email;

    private List<GetAllHouseDTO> houses;




}
