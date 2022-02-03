package i.flowers.database.service.impl;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderForPaypal {

    private String total;
    private String subtotal;
    private String shipping;
    private String tax;
    private List<OrderItemForPaypal> orderItems;

    @Override
    public String toString() {
        return "OrderForPaypal{" +
                "total='" + total + '\'' +
                ", subtotal='" + subtotal + '\'' +
                ", shipping='" + shipping + '\'' +
                ", tax='" + tax + '\'' +
                ", orderItems=" + orderItems +
                '}';
    }
}
