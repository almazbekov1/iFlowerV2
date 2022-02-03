//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package i.flowers.database.dto;

import java.util.Date;
import java.util.List;

public class OrderResponse {
    private String sendersFullName;
    private String sendersPhoneNumber;
    private String address;
    private String recipientFullName;
    private String recipientPhoneNumber;
    private Date timeOfDelivery;
    private String comment;
    private Double distance;
    private List<OrderFlowerObject> orders;
    private Date createdDate;
    private Double price;

    public OrderResponse() {
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

    public List<OrderFlowerObject> getOrders() {
        return this.orders;
    }

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public Double getPrice() {
        return this.price;
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

    public void setOrders(final List<OrderFlowerObject> orders) {
        this.orders = orders;
    }

    public void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }
}
