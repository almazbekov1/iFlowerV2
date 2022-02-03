//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package i.flowers.database.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(
        name = "flowers"
)
@Getter
@Setter
public class FlowerEntity implements Serializable {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @CreatedDate
    @Column(
            name = "created"
    )
    private Date created = new Date();
    @Column(
            unique = true
    )
    private String name;
    private String description;
    private double price;
    private double rating;
    private boolean available;
    private boolean block = false;
    @Enumerated(EnumType.STRING)
    private Category category;
    @OneToMany(
            cascade = {CascadeType.ALL},
            fetch = FetchType.EAGER
    )
    private List<ImageEntity> images;

    public FlowerEntity() {
    }

    public FlowerEntity(String name, String description, double price, double rating, boolean available, List<ImageEntity> images, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.available = available;
        this.images = images;
        this.category = category;
    }

    public String toString() {
        return "FlowerEntity{id=" + this.id + ", created=" + this.created + ", name='" + this.name + "', description='" + this.description + "', price=" + this.price + ", rating=" + this.rating + ", available=" + this.available + ", category=" + this.category + ", images=" + this.images + "}";
    }

    public Long getId() {
        return this.id;
    }

    public Date getCreated() {
        return this.created;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public double getPrice() {
        return this.price;
    }

    public double getRating() {
        return this.rating;
    }

    public boolean isAvailable() {
        return this.available;
    }

    public Category getCategory() {
        return this.category;
    }

    public List<ImageEntity> getImages() {
        return this.images;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setCreated(final Date created) {
        this.created = created;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    public void setRating(final double rating) {
        this.rating = rating;
    }

    public void setAvailable(final boolean available) {
        this.available = available;
    }

    public void setCategory(final Category category) {
        this.category = category;
    }

    public void setImages(final List<ImageEntity> images) {
        this.images = images;
    }
}
