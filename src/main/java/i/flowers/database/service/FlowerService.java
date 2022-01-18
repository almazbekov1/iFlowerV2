package i.flowers.database.service;

import i.flowers.database.dto.FlowerRequest;
import i.flowers.database.dto.FlowerResponse;
import i.flowers.database.model.FlowerEntity;

import java.util.List;

public interface FlowerService {

    FlowerResponse addNewFlower(FlowerRequest flower);

    FlowerResponse updateFlower(FlowerRequest flower,Long id);

    List<FlowerResponse> getAll(int page,int size);

    FlowerResponse findByName(String name);

    FlowerResponse findById(Long id);

    String delete(Long id);
}
