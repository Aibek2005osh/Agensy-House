package java16.service.impl;

import java16.entitys.House;
import java16.entitys.User;
import java16.repo.HouseRepo;
import java16.repo.UserRepo;
import java16.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {


    private  final UserRepo userRepo;
    private final HouseRepo houseRepo;


    @Override
    public List<User> getAllUsers() {
        try {
            return userRepo.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Колдонуучуларды алууда ката кетти", e);
        }
    }

    @Override
    public List<House> getAllHouses() {
        return houseRepo.findAll();
    }
}
