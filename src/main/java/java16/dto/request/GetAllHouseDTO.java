package java16.dto.request;

import java16.entitys.Image;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter @Setter @Builder @NoArgsConstructor
@AllArgsConstructor
public class GetAllHouseDTO {

    private Long id;

    private List<String> images;


    private String description;

    private BigDecimal price;

    private String address;



}
