package java16.dto.request;


import java16.entitys.Image;
import java16.enums.HomeType;
import java16.enums.KyrgyzstanRegion;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddHouseDTO {


    private HomeType homeType;

    private List<String> images;
    private String title;
    private String description;
    private BigDecimal price;
    private int maxOfGuests;
    private KyrgyzstanRegion region;
    private String address;


}


