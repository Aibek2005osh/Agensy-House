package java16.entitys;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private List<String> image;

    @ManyToOne
    private House house;

}
