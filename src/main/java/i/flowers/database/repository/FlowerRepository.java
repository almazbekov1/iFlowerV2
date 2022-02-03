//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package i.flowers.database.repository;

import i.flowers.database.model.Category;
import i.flowers.database.model.FlowerEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FlowerRepository extends JpaRepository<FlowerEntity, Long> {
    Optional<FlowerEntity> findByName(String name);

    @Query("select f from FlowerEntity f where (f.category = :category or :category is null) and (f.available = :available or :available is null)")
    Optional<List<FlowerEntity>> findAll(Category category, Pageable pageable, Boolean available);
}
