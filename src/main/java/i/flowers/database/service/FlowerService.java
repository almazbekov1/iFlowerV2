package i.flowers.database.service;

import i.flowers.database.model.FlowerEntity;

import java.util.List;

public interface FlowerService {

    FlowerEntity addNewFlower(FlowerEntity flower);

    List<FlowerEntity> getAll();

    FlowerEntity findByName(String name);

    FlowerEntity findById(Long id);

    void delete(Long id);
}
