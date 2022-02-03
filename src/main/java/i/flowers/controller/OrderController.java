//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package i.flowers.controller;

import i.flowers.database.dto.OrderRequest;
import i.flowers.database.dto.OrderResponse;
import i.flowers.database.service.OrderService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api"})
@CrossOrigin(
        origins = {"*"},
        maxAge = 3600L
)
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping({"/admin/orders"})
    public ResponseEntity<List<OrderResponse>> getAll() {
        List<OrderResponse> responseList = this.orderService.findAll();
        return new ResponseEntity(responseList, HttpStatus.OK);
    }

    @PostMapping({"/public/orders"})
    public OrderResponse saveOrder(@RequestBody OrderRequest orderRequest) {
        return this.orderService.addNewOrder(orderRequest);
    }

    @GetMapping({"/admin/orders/{id}"})
    public ResponseEntity<OrderResponse> getById(@PathVariable Long id) {
        return new ResponseEntity(this.orderService.findById(id), HttpStatus.OK);
    }

    @PutMapping({"/admin/orders/{id}"})
    public ResponseEntity<OrderResponse> update(@PathVariable Long id, @RequestBody OrderRequest orderRequest) {
        return new ResponseEntity(this.orderService.updateOrder(orderRequest, id), HttpStatus.OK);
    }

    @PutMapping({"/admin/orders/done/{id}"})
    public ResponseEntity<Boolean> done(@PathVariable Long id) {
        return new ResponseEntity(this.orderService.done(id), HttpStatus.OK);
    }

    @DeleteMapping({"/admin/orders/{id}"})
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return new ResponseEntity(this.orderService.delete(id), HttpStatus.OK);
    }
}