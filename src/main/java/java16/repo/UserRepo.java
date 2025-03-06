package java16.repo;

import java16.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface    UserRepo extends JpaRepository<User, Integer> {
    @Query("select count(u) > 0 from User u where u.email = :email")
    boolean existsByEmail(String email);

    @Query("select count(u) > 0 from User u where u.userName = :userName")
    boolean existsByUserName(String userName);


//    @Query("select  u from User u where u.email = :email")
    Optional<User> findByEmail(String email);



}
