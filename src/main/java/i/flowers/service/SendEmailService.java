package i.flowers.service;

import i.flowers.config.MyConstants;
import i.flowers.database.model.OrderEntity;
import i.flowers.database.model.OrderFlowerEntity;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SendEmailService {

    private final JavaMailSender emailSender;

    public boolean sendEmail(OrderEntity orderEntity) {
        String subject = "NEW ORDER";
        String text = "  An order has been placed with you:\n" +
                "      ------------------------------------------\n";
        for (OrderFlowerEntity o : orderEntity.getOrderFlowers()
        ) {
            text += "      name: " + o.getFlower().getName() + "\n" +
                    "      quantity: " + o.getAmount() + "\n" +
                    "      ------------------------------------------\n";
        }
        text += "      when to deliver:  " + orderEntity.getTimeOfDelivery() + "\n\n";
        text += "      address: "+orderEntity.getAddress()+"\n\n";
        text += "      distance: "+orderEntity.getDistance()+" ml\n\n";
        text += "      date of creation: "+orderEntity.getCreated()+" \n\n\n";

        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setTo(MyConstants.FRIEND_EMAIL);
            message.setSubject(subject);
            message.setText(text);

            emailSender.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    static String s =
            "  An order has been placed with you:\n" +
                    "      ------------------------------------------\n" +
                    "      name: name1\n" +
                    "      quantity: 34\n" +
                    "      ------------------------------------------\n" +
                    "      name: name2\n" +
                    "      quantity: 34\n" +
                    "      ------------------------------------------\n" +
                    "      when to deliver:  2021-12-21\n" +
                    "     \n" +
                    "      address: address\n" +
                    "     \n" +
                    "      distance: 9 ml\n" +
                    "     \n" +
                    "      date of creation: 2021-12-21" +
                    "" +
                    "";

}
