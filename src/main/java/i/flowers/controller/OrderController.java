package i.flowers.controller;

import i.flowers.database.dto.OrderNewRequest;
import i.flowers.database.dto.OrderResponse;
import i.flowers.database.dto.OrderUpdateRequest;
import i.flowers.database.service.OrderService;
import java.util.List;

import i.flowers.database.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api"})
@CrossOrigin(
        origins = {"*"},
        maxAge = 3600L
)
public class OrderController {
    private final OrderService orderService;
    private final PaymentService paymentService;

    public OrderController(OrderService orderService, PaymentService paymentService) {
        this.orderService = orderService;
        this.paymentService = paymentService;
    }

    @GetMapping({"/admin/orders"})
    public ResponseEntity<List<OrderResponse>> getAll() {
        List<OrderResponse> responseList = this.orderService.findAll();
        return new ResponseEntity(responseList, HttpStatus.OK);
    }

    @PostMapping({"/public/orders"})
    public OrderResponse saveOrder(@RequestBody OrderNewRequest orderNewRequest) {
        return this.orderService.addNewOrder(orderNewRequest);
    }

    @GetMapping({"/admin/orders/{id}"})
    public ResponseEntity<OrderResponse> getById(@PathVariable Long id) {
        return new ResponseEntity(this.orderService.findById(id), HttpStatus.OK);
    }

    @PutMapping({"/admin/orders/{id}"})
    public ResponseEntity<OrderResponse> update(@PathVariable Long id, @RequestBody OrderUpdateRequest orderNewRequest) {
        return new ResponseEntity(this.orderService.updateOrder(orderNewRequest, id), HttpStatus.OK);
    }

    @PutMapping({"/admin/orders/done/{id}"})
    public ResponseEntity<Boolean> done(@PathVariable Long id) {
        return new ResponseEntity(this.orderService.done(id), HttpStatus.OK);
    }
    @PutMapping({"/admin/orders/payed/{id}"})
    public ResponseEntity<Boolean> payed(@PathVariable Long id) {
        return new ResponseEntity(this.orderService.payed(id), HttpStatus.OK);
    }

    @DeleteMapping({"/admin/orders/{id}"})
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return new ResponseEntity(this.orderService.delete(id), HttpStatus.OK);
    }
    @GetMapping("/public/orders/payment/paypal/{id}")
    public ResponseEntity<String> paypal(@PathVariable Long id){
        return new ResponseEntity<>(paymentService.payForPaypal(id),HttpStatus.OK);
    }
    @GetMapping("/public/orders/payment/zelle/{id}")
    public ResponseEntity<?> zelle(@PathVariable Long id, @RequestParam String zelle){
        return new ResponseEntity<>(paymentService.payForZelle(id,zelle),HttpStatus.OK);
    }
    @GetMapping("/public/orders/payment/other/{id}")
    public ResponseEntity<?> other(@PathVariable Long id){
        return new ResponseEntity<>(paymentService.payForOther(id),HttpStatus.OK);
    }


}
