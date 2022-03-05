package i.flowers.database.service.impl;

import com.paypal.api.payments.*;
import com.paypal.base.rest.PayPalRESTException;
import i.flowers.database.model.OrderEntity;
import i.flowers.database.model.PaymentMethod;
import i.flowers.database.repository.OrderRepository;
import i.flowers.database.service.PaymentService;
import i.flowers.exception.FLowerServiceException;
import i.flowers.exception.OrderServiceException;
import i.flowers.exception.UserServiceException;
import i.flowers.service.PaypalService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

//    public static final String SUCCESS_URL = "http://localhost:6751/api/public/orders/payment/payed?name=";
    public static final String SUCCESS_URL = "http://iflower-env.eba-twvf884n.us-east-1.elasticbeanstalk.com/api/public/orders/payment/payed/";
    public static final String CANCEL_URL = "http://iflowers.s3-website-us-east-1.amazonaws.com/flowers";
//    public static final String CANCEL_URL = "http://localhost:3000/flowers";

    private final OrderRepository orderRepository;
    private final PaypalService paypalService;


    public PaymentServiceImpl(OrderRepository orderRepository, PaypalService paypalService) {
        this.orderRepository = orderRepository;
        this.paypalService = paypalService;
    }

    @Override
    public String payForPaypal(Long id) {
        if (orderRepository.findById(id).isEmpty()) {
            throw new FLowerServiceException("no order found by id: " + id);
        }
        try {
            String href = "произошла ошибка транзакции";
            OrderEntity orderEntity = orderRepository.findById(id).get();
            Payment payment = paypalService.getPayment(orderEntity, CANCEL_URL,
                    SUCCESS_URL+orderEntity.getTransaction());
            for (Links link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    href =  link.getHref();
                }
            }
            orderEntity.setPaymentMethod(PaymentMethod.PAYPAL);
            orderRepository.save(orderEntity);
            return href;
        } catch (PayPalRESTException e) {
            e.printStackTrace();
            throw new UserServiceException("transaction failed \n"+e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new UserServiceException("failed");
        }
    }

    @Override
    public String payForZelle(Long id,String zelle) {
        if (orderRepository.findById(id).isEmpty()) {
            throw new OrderServiceException("no order found by id: " + id);
        }

        OrderEntity orderEntity = orderRepository.getById(id);
        orderEntity.setZelle(zelle);
        orderEntity.setPaymentMethod(PaymentMethod.ZELLE);
        orderRepository.save(orderEntity);
        return "success "+orderEntity.getPaymentMethod();
    }

    @Override
    public String payForOther(Long id) {
        if (orderRepository.findById(id).isEmpty()) {
            throw new OrderServiceException("no order found by id: " + id);
        }
        OrderEntity orderEntity = orderRepository.getById(id);
        orderEntity.setPaymentMethod(PaymentMethod.OTHER);
        orderRepository.save(orderEntity);
        return "success "+orderEntity.getPaymentMethod();
    }

    @Override
    public String payForPaypalSimple(Long id) {
        if (orderRepository.findById(id).isEmpty()) {
            throw new OrderServiceException("no order found by id: " + id);
        }
        OrderEntity orderEntity = orderRepository.getById(id);
        orderEntity.setPaymentMethod(PaymentMethod.PAYPAL);
        orderRepository.save(orderEntity);
        return "success "+orderEntity.getPaymentMethod();
    }


}
