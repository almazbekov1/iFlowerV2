package i.flowers.database.service.impl;


import i.flowers.database.dto.FlowerRequest;
import i.flowers.database.dto.FlowerResponse;
import i.flowers.database.model.FlowerEntity;
import i.flowers.database.repository.FlowerRepository;
import i.flowers.database.service.FlowerService;
import i.flowers.exception.FLowerServiceException;
import i.flowers.exception.UserServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public FlowerResponse addNewFlower(FlowerRequest flower) {
        if (flowerRepository.findByName(flower.getName()).isPresent()){
            throw new FLowerServiceException("Flower already exist");
        }
        FlowerEntity flowerEntity =  flower.toFlower();
        flowerRepository.save(flowerEntity);
        return FlowerResponse.fromFLower(flowerEntity);
    }

    @Override
    public FlowerResponse updateFlower(FlowerRequest flower,Long id) {

        FlowerEntity flowerEntity =  flower.toFlower();
        flowerEntity.setId(id);
        flowerRepository.save(flowerEntity);
        return FlowerResponse.fromFLower(flowerEntity);
    }

    @Override
    public List<FlowerResponse> getAll(int page,int size) {
        Pageable pageable = PageRequest.of(page-1,size);
        List<FlowerResponse> flowerDtoList = new FlowerResponse().fromFlower(flowerRepository.findAll(pageable).getContent());
        return flowerDtoList;
    }

    @Override
    public FlowerResponse findByName(String name) {
        Optional<FlowerEntity> flower = flowerRepository.findByName(name);
        if (flower==null){
            throw new FLowerServiceException("no flower found by name: "+name);
        }
        FlowerResponse response = FlowerResponse.fromFLower(flower.get());
        return response;
    }

    @Override
    public FlowerResponse findById(Long id) {
        Optional<FlowerEntity> flower = flowerRepository.findById(id);
        if (flower==null){
            throw new FLowerServiceException("no flower found by id: "+id);
        }

        FlowerResponse result = FlowerResponse.fromFLower(flower.get());
        return result;
    }

    @Override
    public String delete(Long id) {
        if (flowerRepository.findById(id)==null){
            throw new FLowerServiceException("no flower found by id: "+id);
        }
        flowerRepository.deleteById(id);
        return "remove flower by id: "+id;
    }
}

