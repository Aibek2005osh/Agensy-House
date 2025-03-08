package java16.entitys;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "likes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean isLike; // true = лайк, false = дизлайк

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // Кайсы колдонуучу лайк/дизлайк койгон

    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house; // Кайсы үйгө лайк/дизлайк коюлган (эгер бар болсо)

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comments comment; // Кайсы комментарийге лайк/дизлайк коюлган (эгер бар болсо)
}