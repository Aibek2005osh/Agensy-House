package java16.service.impl;

import java16.repo.HouseRepo;
import java16.service.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {

    private final HouseRepo repo;


}
