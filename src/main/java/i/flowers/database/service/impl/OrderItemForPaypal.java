package i.flowers.database.service.impl;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemForPaypal {

    private String sku;
    private String name;
    private String description;
    private String quantity;
    private String price;
    private String tax;

    @Override
    public String toString() {
        return "OrderItemForPaypal{" +
                "sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", quantity='" + quantity + '\'' +
                ", price='" + price + '\'' +
                ", tax='" + tax + '\'' +
                '}';
    }
}
