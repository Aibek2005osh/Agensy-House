package java16.entitys;

import jakarta.persistence.*;
import java16.enums.KyrgyzstanRegion;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "houses")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString

public class House {

    @Id

    @Column
    private Long id;

    private String title;

    private String description;

    private BigDecimal price;

    private int personSize;

    private String address;

    private LocalDate date;



    @Enumerated(EnumType.STRING)
    private KyrgyzstanRegion region;



    @ManyToOne()
    private User owner;

    @OneToMany(mappedBy = "house")
    private List<Image> images;

    @PrePersist @PreUpdate
    private void onCreate() {
        date = LocalDate.now();
    }


}
