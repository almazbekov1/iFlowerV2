package i.flowers.service;

import i.flowers.database.dto.OrderFlowerObject;
import i.flowers.database.model.OrderEntity;
import i.flowers.database.model.OrderFlowerEntity;
import i.flowers.database.repository.FlowerRepository;
import i.flowers.database.repository.InfoRepository;
import i.flowers.database.service.impl.OrderForPaypal;
import i.flowers.database.service.impl.OrderItemForPaypal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AskForPrice {

    private final FlowerRepository repo;
    private final InfoRepository infoRepository;


    public AskForPrice(FlowerRepository flowerRepository, InfoRepository infoRepository) {
        this.repo = flowerRepository;
        this.infoRepository = infoRepository;
    }


    public OrderForPaypal getOrderForPaypal(OrderEntity order) {
        OrderForPaypal paypal = new OrderForPaypal();
        List<OrderItemForPaypal> paypalItems = new ArrayList<>();
        double total = 0;
        double subtotal = 0;
        String shipping;
        double tax = 0;
        for (OrderFlowerEntity f : order.getOrderFlowers()
        ) {
            OrderItemForPaypal orderItemForPaypal = new OrderItemForPaypal();
            double priceFlower = f.getFlower().getPrice();
            Long amountFlower = f.getAmount();
            double totalFlower = priceFlower * amountFlower;
            orderItemForPaypal.setDescription(f.getFlower().getDescription());
            orderItemForPaypal.setPrice(String.valueOf(priceFlower));
            orderItemForPaypal.setName(f.getFlower().getName());
            orderItemForPaypal.setQuantity(String.valueOf(f.getAmount()));
            orderItemForPaypal.setSku(f.getFlower().getName() + LocalDateTime.now());
            // tax
            double taxDetail = 0;
            orderItemForPaypal.setTax(String.valueOf(taxDetail));
            tax = tax + taxDetail;
            paypalItems.add(orderItemForPaypal);
            subtotal = subtotal + totalFlower;
        }
        Double distancePrice = infoRepository.getById(1l).getPriceForDistance();
        shipping = String.valueOf(order.getDistance() * distancePrice);
        paypal.setSubtotal(String.valueOf(subtotal));
        paypal.setShipping(shipping);
        paypal.setTax(String.valueOf(tax));
        total = subtotal + Double.valueOf(shipping) + tax;
        paypal.setTotal(String.valueOf(total));
        paypal.setOrderItems(paypalItems);

        return paypal;

    }


    public OrderForPaypal getPriceForOrder(List<OrderFlowerObject> orders,Double distance) {
        OrderForPaypal paypal = new OrderForPaypal();
        List<OrderItemForPaypal> paypalItems = new ArrayList<>();
        double total = 0;
        double subtotal = 0;
        String shipping;
        double tax = 0;
        for (OrderFlowerObject f : orders
        ) {
            OrderItemForPaypal orderItemForPaypal = new OrderItemForPaypal();
            double priceFlower = repo.getById(f.getFlowerId()).getPrice();
            Long amountFlower = f.getAmount();
            double totalFlower = priceFlower * amountFlower;
            orderItemForPaypal.setDescription(repo.getById(f.getFlowerId()).getDescription());
            orderItemForPaypal.setPrice(String.valueOf(priceFlower));
            orderItemForPaypal.setName(repo.getById(f.getFlowerId()).getName());
            orderItemForPaypal.setQuantity(String.valueOf(f.getAmount()));
            orderItemForPaypal.setSku(repo.getById(f.getFlowerId()).getName() + LocalDateTime.now());
            // tax
            double taxDetail = 0;
            orderItemForPaypal.setTax(String.valueOf(taxDetail));
            tax = tax + taxDetail;
            paypalItems.add(orderItemForPaypal);
            subtotal = subtotal + totalFlower;
        }
        Double distancePrice = infoRepository.getById(1l).getPriceForDistance();
        shipping = String.valueOf(distance * distancePrice);
        paypal.setSubtotal(String.valueOf(subtotal));
        paypal.setShipping(shipping);
        paypal.setTax(String.valueOf(tax));
        total = subtotal + Double.valueOf(shipping) + tax;
        paypal.setTotal(String.valueOf(total));
        paypal.setOrderItems(paypalItems);

        return paypal;

    }



}
