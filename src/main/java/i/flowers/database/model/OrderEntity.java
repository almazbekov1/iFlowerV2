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

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "orders")
@Getter
@Setter
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
    private String transaction;
    private boolean payed = false;
    private boolean done = false;
    private PaymentMethod paymentMethod;


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

}
