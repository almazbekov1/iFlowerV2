//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package i.flowers.database.mapper;

import i.flowers.database.dto.OrderFlowerObject;
import i.flowers.database.dto.OrderRequest;
import i.flowers.database.model.FlowerEntity;
import i.flowers.database.model.OrderEntity;
import i.flowers.database.model.OrderFlowerEntity;
import i.flowers.database.repository.FlowerRepository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OrderRequestMapper {
    private final FlowerRepository flowerRepository;
    @Value("${price.distance}")
    private Double distance;

    public OrderRequestMapper(FlowerRepository flowerRepository) {
        this.flowerRepository = flowerRepository;
    }

    public OrderEntity toOrder(OrderRequest orderRequest) {
        OrderEntity order = new OrderEntity();
        order.setSendersFullName(orderRequest.getSendersFullName());
        order.setSendersPhoneNumber(orderRequest.getSendersPhoneNumber());
        order.setAddress(orderRequest.getAddress());
        order.setRecipientFullName(orderRequest.getRecipientFullName());
        order.setRecipientPhoneNumber(orderRequest.getRecipientPhoneNumber());
        order.setTimeOfDelivery(orderRequest.getTimeOfDelivery());
        order.setComment(orderRequest.getComment());
        order.setDistance(orderRequest.getDistance());
        order.setOrderFlowers(this.getOrderFlower(orderRequest.getOrders()));
        order = this.setOrderFlower(order);
        double count = 0.0D;

        OrderFlowerObject o;
        for(Iterator var5 = orderRequest.getOrders().iterator(); var5.hasNext(); count += (double)o.getAmount() * ((FlowerEntity)this.flowerRepository.findById(o.getFlowerId()).get()).getPrice()) {
            o = (OrderFlowerObject)var5.next();
        }

        order.setPrice(orderRequest.getDistance() * this.distance + count);
        return order;
    }

    private List<OrderFlowerEntity> getOrderFlower(List<OrderFlowerObject> orders) {
        List<OrderFlowerEntity> orderFlower = new ArrayList();
        Iterator var3 = orders.iterator();

        while(var3.hasNext()) {
            OrderFlowerObject o = (OrderFlowerObject)var3.next();
            OrderFlowerEntity orderFlowerEntity = new OrderFlowerEntity();
            orderFlowerEntity.setAmount(o.getAmount());
            orderFlowerEntity.setPrice(flowerRepository.findById(o.getFlowerId()).get().getPrice() * o.getAmount());
            orderFlowerEntity.setFlower((FlowerEntity)this.flowerRepository.findById(o.getFlowerId()).get());
            orderFlowerEntity.setPriceForOne(((FlowerEntity)this.flowerRepository.getById(o.getFlowerId())).getPrice());
            orderFlower.add(orderFlowerEntity);
        }

        return orderFlower;
    }

    private OrderEntity setOrderFlower(OrderEntity o) {
        Iterator var2 = o.getOrderFlowers().iterator();

        while(var2.hasNext()) {
            OrderFlowerEntity p = (OrderFlowerEntity)var2.next();
            p.setOrder(o);
        }

        return o;
    }
}
