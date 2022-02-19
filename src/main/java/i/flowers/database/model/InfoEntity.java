package i.flowers.database.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(
        name = "info"
)
@Getter
@Setter
public class InfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;
    private String email;
    private String phone;
    private String zelle;
    private Double priceForDistance;
    private String description;
    private String image;



}
