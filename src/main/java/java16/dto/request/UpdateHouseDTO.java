package java16.dto.request;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data @Builder
public class    UpdateHouseDTO {

    private List<String> images;
    private String title;
    private String description;
    private BigDecimal price;
    private int maxOfGuests;
}
