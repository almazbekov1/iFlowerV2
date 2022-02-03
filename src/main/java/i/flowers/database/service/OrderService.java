//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package i.flowers.database.service;

import i.flowers.database.dto.OrderRequest;
import i.flowers.database.dto.OrderResponse;
import java.util.List;

public interface OrderService {
    OrderResponse addNewOrder(OrderRequest order);

    List<OrderResponse> findAll();

    OrderResponse findById(Long id);

    OrderResponse updateOrder(OrderRequest orderRequest, Long id);

    boolean done(Long id);

    boolean delete(Long id);
}
