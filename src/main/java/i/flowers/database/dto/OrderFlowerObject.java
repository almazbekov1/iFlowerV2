//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package i.flowers.database.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderFlowerObject {
    private Long id;
    private Long flowerId;
    private Long amount;
    private Long price;
    private Double discount;

    public OrderFlowerObject() {
    }

    public Long getFlowerId() {
        return this.flowerId;
    }

    public Long getAmount() {
        return this.amount;
    }

    public Long getPrice() {
        return this.price;
    }

    public void setFlowerId(final Long flowerId) {
        this.flowerId = flowerId;
    }

    public void setAmount(final Long amount) {
        this.amount = amount;
    }

    public void setPrice(final Long price) {
        this.price = price;
    }
}
