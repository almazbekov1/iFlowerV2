//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package i.flowers.database.service;

import i.flowers.database.dto.FlowerRequest;
import i.flowers.database.dto.FlowerResponse;
import i.flowers.database.model.Category;
import java.util.List;

public interface FlowerService {
    FlowerResponse addNewFlower(FlowerRequest flower);

    FlowerResponse updateFlower(FlowerRequest flower, Long id);

    List<FlowerResponse> getAll(int page, int size, Category category, Boolean available);

    List<FlowerResponse> getAllForAdmin(int page, int size, Category category, Boolean available);

    FlowerResponse findByName(String name);

    FlowerResponse findById(Long id);

    String delete(Long id);

    Boolean block(Long gid);
}
