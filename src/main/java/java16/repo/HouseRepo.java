package java16.repo;

import java16.entitys.House;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepo extends JpaRepository<House, Integer> {
}
