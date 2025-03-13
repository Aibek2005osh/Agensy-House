package java16.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java16.enums.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(unique = true)
    private String userName;

    @Column(unique = true)
    private String email;

    private String password;

    private String phoneNumber;

    private String imageURL;




    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(mappedBy = "user", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<House> favoriteHouses;


    @OneToMany(cascade = CascadeType.PERSIST)
    private List<House> house;

    @OneToMany(mappedBy = "user")
    private List<Comments> comments;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(role);
    }


    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return email;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return  true;
    }
    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return  true;
    }
    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return  true;
    }
}

