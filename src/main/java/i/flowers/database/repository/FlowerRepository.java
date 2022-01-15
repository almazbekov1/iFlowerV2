package i.flowers.database.repository;

import i.flowers.database.model.FlowerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FlowerRepository extends JpaRepository<FlowerEntity,Long> {
    Optional<FlowerEntity> findByName(String name);
}
