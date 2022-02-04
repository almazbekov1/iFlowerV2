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
import i.flowers.database.model.PaymentMethod;
import i.flowers.database.repository.FlowerRepository;

import java.time.LocalDateTime;
import java.util.*;

import i.flowers.database.service.impl.OrderForPaypal;
import i.flowers.service.AskForPrice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OrderRequestMapper {

    private final FlowerRepository flowerRepository;
    private final AskForPrice askForPrice;

    public OrderRequestMapper(FlowerRepository flowerRepository, AskForPrice askForPrice) {
        this.flowerRepository = flowerRepository;
        this.askForPrice = askForPrice;
    }

    public OrderEntity toOrder(OrderRequest orderRequest) {
        OrderEntity order = new OrderEntity();
        OrderForPaypal paypal = askForPrice.getPriceForOrder(orderRequest.getOrders()
                ,orderRequest.getDistance());
        order.setSendersFullName(orderRequest.getSendersFullName());
        order.setSendersPhoneNumber(orderRequest.getSendersPhoneNumber());
        order.setAddress(orderRequest.getAddress());
        order.setRecipientFullName(orderRequest.getRecipientFullName());
        order.setRecipientPhoneNumber(orderRequest.getRecipientPhoneNumber());
        order.setTimeOfDelivery(orderRequest.getTimeOfDelivery());
        order.setComment(orderRequest.getComment());
        order.setDistance(orderRequest.getDistance());
        order.setPaymentMethod(PaymentMethod.OTHER);
        order.setTransaction(LocalDateTime.now() + "" +new Random(9000000)+1000000);
        order.setOrderFlowers(this.getOrderFlower(orderRequest.getOrders()));
        order = this.setOrderFlower(order);
        order.setPrice(Double.valueOf(paypal.getTotal()));
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
