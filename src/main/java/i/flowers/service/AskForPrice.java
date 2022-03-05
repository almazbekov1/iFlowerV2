package i.flowers.service;

import i.flowers.database.dto.OrderFlowerObject;
import i.flowers.database.model.FlowerEntity;
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
            double percent = f.getFlower().getPrice() / 100;
            percent = percent * f.getFlower().getDiscount();
            double priceFlower = f.getFlower().getPrice() - percent;
            priceFlower = Math.round(priceFlower * 10);
            priceFlower = priceFlower / 10;
            Long amountFlower = f.getAmount();
            double totalFlower = priceFlower * amountFlower;
            totalFlower = Math.round(totalFlower * 10);
            totalFlower = totalFlower / 10;
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
        shipping = String.valueOf(Math.round(order.getDistance() * distancePrice));
        paypal.setSubtotal(String.valueOf(subtotal));
        paypal.setShipping(shipping);
        paypal.setTax(String.valueOf(tax));
        total = subtotal + Double.valueOf(shipping) + tax;
        paypal.setTotal(String.valueOf(total));
        paypal.setOrderItems(paypalItems);

        return paypal;

    }


    public OrderForPaypal getPriceForOrder(List<OrderFlowerObject> orders, Double distance) {
        OrderForPaypal paypal = new OrderForPaypal();
        List<OrderItemForPaypal> paypalItems = new ArrayList<>();
        double total = 0;
        double subtotal = 0;
        String shipping;
        double tax = 0;
        for (OrderFlowerObject f : orders
        ) {
            OrderItemForPaypal orderItemForPaypal = new OrderItemForPaypal();
            FlowerEntity flower = repo.getById(f.getFlowerId());
            double percent = flower.getPrice() / 100;
            percent = percent * flower.getDiscount();
            double priceFlower = flower.getPrice() - percent;
            Long amountFlower = f.getAmount();
            double totalFlower = priceFlower * amountFlower;
            totalFlower = Math.round(totalFlower * 10);
            totalFlower = totalFlower / 10;
            orderItemForPaypal.setDescription(flower.getDescription());
            orderItemForPaypal.setPrice(String.valueOf(priceFlower));
            orderItemForPaypal.setName(flower.getName());
            orderItemForPaypal.setQuantity(String.valueOf(f.getAmount()));
            orderItemForPaypal.setSku(flower.getName() + LocalDateTime.now());
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
        total = Math.round(total * 10);
        total = total / 10;
        paypal.setTotal(String.valueOf(total));
        paypal.setOrderItems(paypalItems);

        return paypal;
    }
}
