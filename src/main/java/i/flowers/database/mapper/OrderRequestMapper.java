//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package i.flowers.database.mapper;

import i.flowers.database.dto.OrderFlowerObject;
import i.flowers.database.dto.OrderNewRequest;
import i.flowers.database.dto.OrderUpdateRequest;
import i.flowers.database.model.FlowerEntity;
import i.flowers.database.model.OrderEntity;
import i.flowers.database.model.OrderFlowerEntity;
import i.flowers.database.model.PaymentMethod;
import i.flowers.database.repository.FlowerRepository;

import java.util.*;

import i.flowers.database.repository.OrderRepository;
import i.flowers.database.service.impl.OrderForPaypal;
import i.flowers.exception.OrderServiceException;
import i.flowers.service.AskForPrice;
import i.flowers.service.DistanceMatrixService;
import i.flowers.service.json.DistanceResponse;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderRequestMapper {

    private final FlowerRepository flowerRepository;
    private final AskForPrice askForPrice;
    private final OrderRepository orderRepository;
    private final DistanceMatrixService distanceMatrixService;



    public OrderEntity toOrder(OrderNewRequest orderNewRequest) {
        OrderEntity order = new OrderEntity();
        DistanceResponse distanceResponse = getDistance(orderNewRequest.getAddress());
        Double distance = distanceResponse.getDistance();


        OrderForPaypal paypal = askForPrice.getPriceForOrder(orderNewRequest.getOrders()
                , distance);
        order.setSendersFullName(orderNewRequest.getSendersFullName());
        order.setSendersPhoneNumber(orderNewRequest.getSendersPhoneNumber());
        order.setAddress(distanceResponse.getDestination_addresses().get(0));
        order.setRecipientFullName(orderNewRequest.getRecipientFullName());
        order.setRecipientPhoneNumber(orderNewRequest.getRecipientPhoneNumber());
        order.setTimeOfDelivery(orderNewRequest.getTimeOfDelivery());
        order.setComment(orderNewRequest.getComment());
        order.setDistance(distance);
        order.setPaymentMethod(PaymentMethod.OTHER);
        order.setTransaction(RandomStringUtils.random(50, true, true)+Calendar.getInstance().getTimeInMillis());
        order.setOrderFlowers(this.getOrderFlower(orderNewRequest.getOrders()));
        order = this.setOrderFlower(order);
        order.setPrice(Double.valueOf(paypal.getTotal()));
        return order;
    }
    public OrderEntity toOrder(OrderUpdateRequest orderUpdateRequest,Long id) {
        Optional<OrderEntity> orderEntity = orderRepository.findById(id);
        if (orderEntity.isEmpty()){
            throw new OrderServiceException("order not found");
        }
        OrderEntity order = orderEntity.get();
        order.setSendersFullName(orderUpdateRequest.getSendersFullName());
        order.setSendersPhoneNumber(orderUpdateRequest.getSendersPhoneNumber());
        order.setAddress(orderUpdateRequest.getAddress());
        order.setRecipientFullName(orderUpdateRequest.getRecipientFullName());
        order.setRecipientPhoneNumber(orderUpdateRequest.getRecipientPhoneNumber());
        order.setTimeOfDelivery(orderUpdateRequest.getTimeOfDelivery());
        order.setComment(orderUpdateRequest.getComment());
        order.setDistance(orderUpdateRequest.getDistance());
        order.setPaymentMethod(PaymentMethod.OTHER);
        order.setPayed(orderUpdateRequest.getPayed());
        order.setDone(orderUpdateRequest.getDone());
        order.setOrderFlowers(this.getOrderFlower(orderUpdateRequest.getOrders()));
        order = this.setOrderFlower(order);
        order.setPrice(orderUpdateRequest.getPrice());
        return order;
    }


    private List<OrderFlowerEntity> getOrderFlower(List<OrderFlowerObject> orders) {
        List<OrderFlowerEntity> orderFlower = new ArrayList();
        Iterator var3 = orders.iterator();

        while(var3.hasNext()) {
            OrderFlowerObject o = (OrderFlowerObject)var3.next();
            OrderFlowerEntity orderFlowerEntity = new OrderFlowerEntity();
            if (o.getId()!=null){
                orderFlowerEntity.setId(o.getId());
            }
            FlowerEntity flower = flowerRepository.getById(o.getFlowerId());
            double percent = flower.getPrice() / 100;
            percent = percent * flower.getDiscount();
            double priceFlower = flower.getPrice() - percent;
            Long amountFlower = o.getAmount();
            double totalFlower = priceFlower * amountFlower;
            orderFlowerEntity.setAmount(o.getAmount());
            orderFlowerEntity.setPrice(totalFlower);
            orderFlowerEntity.setDiscount(flower.getDiscount());
            orderFlowerEntity.setFlower(flower);
            orderFlowerEntity.setPriceForOne(flower.getPrice());
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
    private DistanceResponse getDistance(String to){
        return distanceMatrixService.callAndParse(to);

    }
}
