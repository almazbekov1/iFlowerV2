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
    private String flowerName;
    private Long flowerId;
    private Long amount;
    private Double price;
    private Double discount;
    private FlowerResponse flower;

    public OrderFlowerObject() {
    }

    public Long getFlowerId() {
        return this.flowerId;
    }

    public Long getAmount() {
        return this.amount;
    }

    public void setFlowerId(final Long flowerId) {
        this.flowerId = flowerId;
    }

    public void setAmount(final Long amount) {
        this.amount = amount;
    }

}
