package i.flowers.database.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "flowers")
public class FlowerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(name = "created")
    @Transient
    private Date created;

    @Column(unique = true)
    private String name;

    private String description;

    private double price;

    private boolean available;

    @OneToMany(cascade = CascadeType.ALL
            , fetch = FetchType.EAGER
    )
    private List<ImageEntity> images;


}