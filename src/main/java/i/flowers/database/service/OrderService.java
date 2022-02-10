//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package i.flowers.database.service;

import i.flowers.database.dto.OrderNewRequest;
import i.flowers.database.dto.OrderResponse;
import i.flowers.database.dto.OrderUpdateRequest;

import java.util.List;

public interface OrderService {
    OrderResponse addNewOrder(OrderNewRequest order);

    List<OrderResponse> findAll();

    OrderResponse findById(Long id);

    OrderResponse updateOrder(OrderUpdateRequest orderNewRequest, Long id);

    boolean done(Long id);

    boolean payed(Long id);

    boolean delete(Long id);
}
