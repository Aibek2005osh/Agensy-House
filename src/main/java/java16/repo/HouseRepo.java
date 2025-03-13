package java16.repo;

import java16.entitys.House;

import java16.exceptions.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepo extends JpaRepository<House, Long> {


    default House findByHouseId(Long id) {
        return findById(id).orElseThrow(() -> new NotFoundException(String.format("not  House with : %s", id)));

    }

}
