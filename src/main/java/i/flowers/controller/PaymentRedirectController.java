package i.flowers.controller;

import i.flowers.database.model.OrderEntity;
import i.flowers.database.repository.OrderRepository;
import i.flowers.service.AskForPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PaymentRedirectController {
    //
    @Autowired
    AskForPrice payService;

    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/api/public/orders/payment/payed/{transaction}")
    public String successPayed(@PathVariable String transaction){
        if (orderRepository.findByTransaction(transaction).isPresent()){
            OrderEntity o = orderRepository.findByTransaction(transaction).get();
            o.setPayed(true);
            orderRepository.save(o);
            return "redirect:http://localhost:3000/flowers?name="+o.getSendersFullName();
        }else {
            return "redirect:https://englishlib.org/dictionary/en-ru/failed.html";
        }


    }

}
