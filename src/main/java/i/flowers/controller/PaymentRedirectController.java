package i.flowers.controller;

import i.flowers.database.model.OrderEntity;
import i.flowers.database.repository.OrderRepository;
import i.flowers.service.AskForPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class PaymentRedirectController {
    //

//    public static final String SUCCESS_URL = "redirect:localhost:3000/flowers?name=";
        public static final String SUCCESS_URL = "redirect:http://iflowers.s3-website-us-east-1.amazonaws.com/flowers?name=";
        public static final String FAILED_URL = "redirect:http://iflowers.s3-website-us-east-1.amazonaws.com/flowers?failed=";
//    public static final String FAILED_URL = "redirect:localhost:3000/flowers?failed=";

    @Autowired
    AskForPrice payService;

    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/ga")
    public String get() {
        System.out.println("ha");
        return "ds";
    }

    @GetMapping("/api/public/orders/payment/payed/{transaction}")
    public String successPayed(@PathVariable String transaction) {
        if (orderRepository.findByTransaction(transaction).isEmpty()){
            return FAILED_URL+"NOT_FOUND_TRANSACTION";
        }
        OrderEntity o = null;
        try {
            o = orderRepository.findByTransaction(transaction).get();
        } catch (Exception e) {
            return FAILED_URL + o.getSendersFullName();
        }
        if (orderRepository.findByTransaction(transaction).isPresent()) {
            o.setPayed(true);
            orderRepository.save(o);
            System.out.println(SUCCESS_URL + o.getSendersFullName());
            return SUCCESS_URL + o.getSendersFullName();
        } else {
            return FAILED_URL + o.getSendersFullName();
        }


    }

}
