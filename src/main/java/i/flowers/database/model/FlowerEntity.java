package i.flowers.database.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
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
    private double rating;

    private boolean available;

    @OneToMany(cascade = CascadeType.ALL
            , fetch = FetchType.EAGER
    )
    private List<ImageEntity> images;

    public FlowerEntity() {
    }

    public FlowerEntity(String name, String description, double price, double rating, boolean available, List<ImageEntity> images) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.available = available;
        this.images = images;
    }

    @Override
    public String toString() {
        return "FlowerEntity{" +
                "id=" + id +
                ", created=" + created +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                ", available=" + available +
                ", images=" + images +
                '}';
    }
}