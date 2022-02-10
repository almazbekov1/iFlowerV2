//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package i.flowers.database.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(
        name = "order_flowers"
)
@Getter
@Setter
public class OrderFlowerEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @OneToOne(
            fetch = FetchType.EAGER
    )
    private FlowerEntity flower;
    private Long amount;
    private Double price;
    private Double priceForOne;
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    private OrderEntity order;

    private Double discount = 0d;

    public OrderFlowerEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public FlowerEntity getFlower() {
        return this.flower;
    }

    public Long getAmount() {
        return this.amount;
    }

    public Double getPrice() {
        return this.price;
    }

    public Double getPriceForOne() {
        return this.priceForOne;
    }

    public OrderEntity getOrder() {
        return this.order;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setFlower(final FlowerEntity flower) {
        this.flower = flower;
    }

    public void setAmount(final Long amount) {
        this.amount = amount;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }

    public void setPriceForOne(final Double priceForOne) {
        this.priceForOne = priceForOne;
    }

    public void setOrder(final OrderEntity order) {
        this.order = order;
    }
}
