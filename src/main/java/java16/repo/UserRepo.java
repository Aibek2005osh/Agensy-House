package java16.repo;

import java16.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Integer> {
    @Query("select count(u) > 0 from User u where u.email = :email")
    boolean existsByEmail(String email);

    @Query("select count(u) > 0 from User u where u.userName = :userName")
    boolean existsByUserName(String userName);

    User findByEmail(String email);
}
