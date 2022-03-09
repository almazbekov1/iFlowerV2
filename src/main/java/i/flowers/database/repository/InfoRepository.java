package i.flowers.database.repository;

import i.flowers.database.model.InfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfoRepository extends JpaRepository<InfoEntity,Long> {
}
