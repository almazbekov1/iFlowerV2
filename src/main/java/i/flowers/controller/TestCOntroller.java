package i.flowers.controller;

import i.flowers.database.repository.OrderRepository;
import i.flowers.service.AskForPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestCOntroller {
    //
    @Autowired
    AskForPrice payService;

    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/get")
    public String get() {
//        payService.getOrder(orderRepository.getById(1l));
        return "test";
    }
}
