package i.flowers.database.service.impl;

import com.paypal.api.payments.*;
import com.paypal.base.rest.PayPalRESTException;
import i.flowers.database.model.OrderEntity;
import i.flowers.database.model.PaymentMethod;
import i.flowers.database.repository.OrderRepository;
import i.flowers.database.service.PaymentService;
import i.flowers.exception.FLowerServiceException;
import i.flowers.exception.UserServiceException;
import i.flowers.service.PaypalService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    public static final String SUCCESS_URL = "http://localhost:6751/api/public/orders/payment/payed?name=";
    public static final String CANCEL_URL = "https://music.yandex.ru/home";

    private final OrderRepository orderRepository;
    private final PaypalService paypalService;


    public PaymentServiceImpl(OrderRepository orderRepository, PaypalService paypalService) {
        this.orderRepository = orderRepository;
        this.paypalService = paypalService;
    }

    @Override
    public String payForPaypal(Long id) {
        if (orderRepository.findById(id).isEmpty()) {
            throw new FLowerServiceException("no flower found by id: " + id);
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
            throw new UserServiceException("transaction failed");
        } catch (Exception e) {
            e.printStackTrace();
            throw new UserServiceException("failed");
        }
    }

    @Override
    public String payForZelle(Long id) {
        if (orderRepository.findById(id).isEmpty()) {
            throw new FLowerServiceException("no flower found by id: " + id);
        }
        OrderEntity orderEntity = orderRepository.getById(id);
        orderEntity.setPaymentMethod(PaymentMethod.ZELLE);
        return "success "+orderEntity.getPaymentMethod();
    }

    @Override
    public String payForOther(Long id) {
        if (orderRepository.findById(id).isEmpty()) {
            throw new FLowerServiceException("no flower found by id: " + id);
        }
        OrderEntity orderEntity = orderRepository.getById(id);
        orderEntity.setPaymentMethod(PaymentMethod.OTHER);
        return "success "+orderEntity.getPaymentMethod();
    }



}
