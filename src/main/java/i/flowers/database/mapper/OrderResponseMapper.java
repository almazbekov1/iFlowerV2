//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package i.flowers.database.mapper;

import i.flowers.database.dto.OrderFlowerObject;
import i.flowers.database.dto.OrderResponse;
import i.flowers.database.model.OrderEntity;
import i.flowers.database.model.OrderFlowerEntity;
import i.flowers.database.repository.FlowerRepository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class OrderResponseMapper {
    private final FlowerRepository flowerRepository;

    public OrderResponseMapper(FlowerRepository flowerRepository) {
        this.flowerRepository = flowerRepository;
    }

    public OrderResponse fromOrder(OrderEntity orderEntity) {
        OrderResponse order = new OrderResponse();
        order.setSendersFullName(orderEntity.getSendersFullName());
        order.setSendersPhoneNumber(orderEntity.getSendersPhoneNumber());
        order.setAddress(orderEntity.getAddress());
        order.setRecipientFullName(orderEntity.getRecipientFullName());
        order.setRecipientPhoneNumber(orderEntity.getRecipientPhoneNumber());
        order.setTimeOfDelivery(orderEntity.getTimeOfDelivery());
        order.setComment(orderEntity.getComment());
        order.setDistance(orderEntity.getDistance());
        order.setOrders(this.getOrderFlower(orderEntity.getOrderFlowers()));
        order.setCreatedDate(orderEntity.getCreated());
        order.setPrice(orderEntity.getPrice());
        return order;
    }

    public List<OrderResponse> fromOrder(List<OrderEntity> orderEntities) {
        List<OrderResponse> orderResponses = new ArrayList();
        Iterator var3 = orderEntities.iterator();

        while(var3.hasNext()) {
            OrderEntity o = (OrderEntity)var3.next();
            orderResponses.add(this.fromOrder(o));
        }

        return orderResponses;
    }

    private List<OrderFlowerObject> getOrderFlower(List<OrderFlowerEntity> orders) {
        List<OrderFlowerObject> orderFlower = new ArrayList();
        Iterator var3 = orders.iterator();

        while(var3.hasNext()) {
            OrderFlowerEntity o = (OrderFlowerEntity)var3.next();
            OrderFlowerObject orderFlowerObject = new OrderFlowerObject();
            orderFlowerObject.setAmount(o.getAmount());
            orderFlowerObject.setFlowerId(o.getFlower().getId());
            orderFlowerObject.setPrice((long)((double)o.getAmount() * o.getFlower().getPrice()));
            orderFlower.add(orderFlowerObject);
        }

        return orderFlower;
    }
}