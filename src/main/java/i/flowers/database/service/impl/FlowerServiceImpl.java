//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package i.flowers.database.service.impl;

import i.flowers.database.dto.FlowerRequest;
import i.flowers.database.dto.FlowerResponse;
import i.flowers.database.model.Category;
import i.flowers.database.model.FlowerEntity;
import i.flowers.database.repository.FlowerRepository;
import i.flowers.database.service.FlowerService;
import i.flowers.exception.FLowerServiceException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FlowerServiceImpl implements FlowerService {
    private static final Logger log = LoggerFactory.getLogger(FlowerServiceImpl.class);
    private final FlowerRepository flowerRepository;

    public FlowerServiceImpl(FlowerRepository flowerRepository) {
        this.flowerRepository = flowerRepository;
    }

    public FlowerResponse addNewFlower(FlowerRequest flower) {
        if (this.flowerRepository.findByName(flower.getName()).isPresent()) {
            throw new FLowerServiceException("Flower already exist");
        } else {
            FlowerEntity flowerEntity = flower.toFlower();
            this.flowerRepository.save(flowerEntity);
            return FlowerResponse.fromFLower(flowerEntity);
        }
    }

    public FlowerResponse updateFlower(FlowerRequest flower, Long id) {
        FlowerEntity flowerEntity = flower.toFlower();
        flowerEntity.setId(id);
        this.flowerRepository.save(flowerEntity);
        return FlowerResponse.fromFLower(flowerEntity);
    }

    public List<FlowerResponse> getAll(int page, int size, Category category, Boolean available) {
        Pageable pageable = PageRequest.of(page - 1, size);
        List<FlowerResponse> flowerDtoList = (new FlowerResponse()).fromFlower((List)this.flowerRepository.findAll(category, pageable, available).get());
        return flowerDtoList;
    }

    public FlowerResponse findByName(String name) {
        Optional<FlowerEntity> flower = this.flowerRepository.findByName(name);
        if (flower == null) {
            throw new FLowerServiceException("no flower found by name: " + name);
        } else {
            FlowerResponse response = FlowerResponse.fromFLower((FlowerEntity)flower.get());
            return response;
        }
    }

    public FlowerResponse findById(Long id) {
        Optional<FlowerEntity> flower = this.flowerRepository.findById(id);
        if (flower == null) {
            throw new FLowerServiceException("no flower found by id: " + id);
        } else {
            FlowerResponse result = FlowerResponse.fromFLower((FlowerEntity)flower.get());
            return result;
        }
    }

    public String delete(Long id) {
        if (this.flowerRepository.findById(id) == null) {
            throw new FLowerServiceException("no flower found by id: " + id);
        } else {
            this.flowerRepository.deleteById(id);
            return "remove flower by id: " + id;
        }
    }
}
