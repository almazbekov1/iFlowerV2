package i.flowers.database.service.impl;


import i.flowers.database.model.FlowerEntity;
import i.flowers.database.repository.FlowerRepository;
import i.flowers.database.service.FlowerService;
import i.flowers.exception.FLowerServiceException;
import i.flowers.exception.UserServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class FlowerServiceImpl implements FlowerService {

    private final FlowerRepository flowerRepository;

    public FlowerServiceImpl(FlowerRepository flowerRepository) {
        this.flowerRepository = flowerRepository;
    }

    @Override
    public FlowerEntity addNewFlower(FlowerEntity flower) {
        if (findByName(flower.getName())!=null){
            throw new FLowerServiceException("Flower already exist");
        }
        return flowerRepository.save(flower);
    }

    @Override
    public List<FlowerEntity> getAll() {
        List<FlowerEntity> flowerDtoList = flowerRepository.findAll();
        return flowerDtoList;
    }

    @Override
    public FlowerEntity findByName(String name) {
        Optional<FlowerEntity> result = flowerRepository.findByName(name);
        if (result==null){
            throw new FLowerServiceException("no flower found by name: "+name);
        }
        return result.get();
    }

    @Override
    public FlowerEntity findById(Long id) {
        Optional<FlowerEntity> result = flowerRepository.findById(id);
        if (result==null){
            throw new FLowerServiceException("no flower found by id: "+id);
        }

        return result.get();
    }

    @Override
    public void delete(Long id) {
        if (flowerRepository.findById(id)==null){
            throw new FLowerServiceException("no flower found by id: "+id);
        }
        flowerRepository.deleteById(id);
    }
}

