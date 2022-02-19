//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package i.flowers.database.repository;

import i.flowers.database.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    Optional<OrderEntity> findByTransaction(String name);

    @Query("select o from OrderEntity o where o.done = false order by o.created asc ")
    List<OrderEntity> findAllNotDone();
    @Query("select o from OrderEntity o where o.done = true")
    List<OrderEntity> findAllDone();
}
