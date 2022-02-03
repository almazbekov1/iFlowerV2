//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package i.flowers.database.model;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(
        name = "orders"
)
public class OrderEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String sendersFullName;
    private String sendersPhoneNumber;
    private String address;
    private String recipientFullName;
    private String recipientPhoneNumber;
    private Date timeOfDelivery;
    private String comment;
    private Double distance;
    private Double price;
    private boolean payed = false;
    private boolean done = false;
    @OneToMany(
            mappedBy = "order",
            cascade = {CascadeType.ALL},
            fetch = FetchType.EAGER
    )
    private List<OrderFlowerEntity> orderFlowers;
    @CreatedDate
    @Column(
            name = "created"
    )
    private Date created = new Date();

    public OrderEntity() {
    }

    public String toString() {
        return "OrderEntity{id=" + this.id + ", sendersFullName='" + this.sendersFullName + "', sendersPhoneNumber='" + this.sendersPhoneNumber + "', address='" + this.address + "', recipientFullName='" + this.recipientFullName + "', recipientPhoneNumber='" + this.recipientPhoneNumber + "', timeOfDelivery='" + this.timeOfDelivery + "', comment='" + this.comment + "', distance=" + this.distance + ", price=" + this.price + ", orderFlowers=" + this.orderFlowers + ", created=" + this.created + "}";
    }

    public Long getId() {
        return this.id;
    }

    public String getSendersFullName() {
        return this.sendersFullName;
    }

    public String getSendersPhoneNumber() {
        return this.sendersPhoneNumber;
    }

    public String getAddress() {
        return this.address;
    }

    public String getRecipientFullName() {
        return this.recipientFullName;
    }

    public String getRecipientPhoneNumber() {
        return this.recipientPhoneNumber;
    }

    public Date getTimeOfDelivery() {
        return this.timeOfDelivery;
    }

    public String getComment() {
        return this.comment;
    }

    public Double getDistance() {
        return this.distance;
    }

    public Double getPrice() {
        return this.price;
    }

    public boolean isPayed() {
        return this.payed;
    }

    public boolean isDone() {
        return this.done;
    }

    public List<OrderFlowerEntity> getOrderFlowers() {
        return this.orderFlowers;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setSendersFullName(final String sendersFullName) {
        this.sendersFullName = sendersFullName;
    }

    public void setSendersPhoneNumber(final String sendersPhoneNumber) {
        this.sendersPhoneNumber = sendersPhoneNumber;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public void setRecipientFullName(final String recipientFullName) {
        this.recipientFullName = recipientFullName;
    }

    public void setRecipientPhoneNumber(final String recipientPhoneNumber) {
        this.recipientPhoneNumber = recipientPhoneNumber;
    }

    public void setTimeOfDelivery(final Date timeOfDelivery) {
        this.timeOfDelivery = timeOfDelivery;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }

    public void setDistance(final Double distance) {
        this.distance = distance;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }

    public void setPayed(final boolean payed) {
        this.payed = payed;
    }

    public void setDone(final boolean done) {
        this.done = done;
    }

    public void setOrderFlowers(final List<OrderFlowerEntity> orderFlowers) {
        this.orderFlowers = orderFlowers;
    }

    public void setCreated(final Date created) {
        this.created = created;
    }
}
