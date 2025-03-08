package java16.entitys;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "images")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString

public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;

    private LocalDate createDate;

    @ManyToOne
    private House house;

    @PrePersist
    public void prePersist() {
        this.createDate = LocalDate.now();
    }


}
