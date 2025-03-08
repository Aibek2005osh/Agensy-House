package java16.entitys;

import jakarta.persistence.*;
import java16.enums.HomeType;
import java16.enums.KyrgyzstanRegion;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "houses")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString

public class House  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Enumerated(EnumType.STRING)
    private HomeType homeType;

    private String title;

    private String description;

    private BigDecimal price;

    private int maxOfGuests;

    @Enumerated(EnumType.STRING)
    private KyrgyzstanRegion region;

    private String address;



    private LocalDate date;


    @ManyToOne()
    private User owner;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();


    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<User> user;


    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL) // Жаңы байланыш
    private List<Likes> likes = new ArrayList<>(); // Үйгө коюлган лайктар/дизлайктар

    @PrePersist
    private void onCreate() {
        date = LocalDate.now();
    }




}
