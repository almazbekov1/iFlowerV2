//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package i.flowers.database.service.impl;

import i.flowers.database.dto.OrderFlowerObject;
import i.flowers.database.dto.OrderNewRequest;
import i.flowers.database.dto.OrderResponse;
import i.flowers.database.dto.OrderUpdateRequest;
import i.flowers.database.mapper.OrderRequestMapper;
import i.flowers.database.mapper.OrderResponseMapper;
import i.flowers.database.model.OrderEntity;
import i.flowers.database.repository.FlowerRepository;
import i.flowers.database.repository.OrderRepository;
import i.flowers.database.service.OrderService;
import i.flowers.exception.FLowerServiceException;

import java.util.*;

import i.flowers.service.DistanceMatrixService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderRequestMapper orderRequestMapper;
    private final OrderResponseMapper orderResponseMapper;
    private final FlowerRepository flowerRepository;
    private final DistanceMatrixService distanceMatrixService;


    public OrderResponse addNewOrder(OrderNewRequest order) {
        Iterator var2 = order.getOrders().iterator();

        OrderFlowerObject o;
        do {
            if (!var2.hasNext()) {
                OrderEntity orderEntity = this.orderRequestMapper.toOrder(order);
                return this.orderResponseMapper.fromOrder((OrderEntity) this.orderRepository.save(orderEntity));
            }

            o = (OrderFlowerObject) var2.next();
        } while (!this.flowerRepository.findById(o.getFlowerId()).isEmpty());

        throw new FLowerServiceException("flower not found/ id: " + o.getFlowerId());
    }

    public List<OrderResponse> findAll() {
//        List<OrderResponse> orderResponses = this.orderResponseMapper.fromOrder(this.orderRepository.findAll());
        List<OrderResponse> orderResponses1 = orderResponseMapper.fromOrder(this.orderRepository.findAllNotDone());
        List<OrderResponse> orderResponses2 = orderResponseMapper.fromOrder(this.orderRepository.findAllDone());

        orderResponses1.addAll(orderResponses2);

        return orderResponses1;
    }

    public OrderResponse findById(Long id) {
        Optional<OrderEntity> orderEntity = this.orderRepository.findById(id);
        if (orderEntity.isEmpty()) {
            throw new FLowerServiceException("Flower not found/ id: " + id);
        } else {
            OrderResponse orderResponse = this.orderResponseMapper.fromOrder((OrderEntity) orderEntity.get());
            return orderResponse;
        }
    }

    @Override
    public OrderResponse updateOrder(OrderUpdateRequest orderNewRequest, Long id) {
        OrderEntity orderEntity = this.orderRequestMapper.toOrder(orderNewRequest, id);

        return this.orderResponseMapper.fromOrder((OrderEntity) this.orderRepository.save(orderEntity));

    }

    public boolean done(Long id) {
        if (this.orderRepository.findById(id).isEmpty()) {
            throw new FLowerServiceException("flower not found/ id: " + id);
        } else {
            OrderEntity orderEntity = (OrderEntity) this.orderRepository.getById(id);
            orderEntity.setPayed(true);
            boolean response;
            if (orderEntity.isDone()) {
                orderEntity.setDone(false);
                response = false;
            } else {
                orderEntity.setDone(true);
                response = true;
            }
            orderRepository.save(orderEntity);
            return response;
        }
    }

    @Override
    public boolean payed(Long id) {
        if (this.orderRepository.findById(id).isEmpty()) {
            throw new FLowerServiceException("flower not found/ id: " + id);
        } else {
            OrderEntity orderEntity = (OrderEntity) this.orderRepository.getById(id);
            boolean response;
            if (orderEntity.isPayed()) {
                orderEntity.setPayed(false);
                response = false;
            } else {
                orderEntity.setPayed(true);
                response = true;
            }
            orderRepository.save(orderEntity);
            return response;
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            if (this.orderRepository.findById(id).isEmpty()) {
                throw new FLowerServiceException("flower not found/ id: " + id);
            } else {
                this.orderRepository.deleteById(id);
                return true;
            }
        } catch (Exception var3) {
            var3.printStackTrace();
            return false;
        }
    }

}