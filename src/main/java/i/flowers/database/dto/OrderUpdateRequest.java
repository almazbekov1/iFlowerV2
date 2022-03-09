package i.flowers.database.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Setter
@Getter
public class OrderUpdateRequest {

    private String sendersFullName;
    private String sendersPhoneNumber;
    private String address;
    private String recipientFullName;
    private String recipientPhoneNumber;
    private Date timeOfDelivery;
    private String comment;
    private Double distance;
    private List<OrderFlowerObject> orders;
    private Boolean done;
    private Boolean payed;
    private Double price;

}
