//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package i.flowers.database.dto;

import i.flowers.database.model.Category;
import i.flowers.database.model.FlowerEntity;
import i.flowers.database.model.ImageEntity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class FlowerRequest {
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private double price;
    @NotNull
    private double rating;
    @NotNull
    private boolean available;
    @NotNull
    private List<String> imageNames;
    @NotNull
    private Category category;

    private Double discount;

    public FlowerRequest() {
    }

    public FlowerEntity toFlower() {
        return new FlowerEntity(this.name, this.description, this.price, this.rating, this.available, this.getImages(this.imageNames), this.category,this.discount);
    }

    private List<ImageEntity> getImages(List<String> strings) {
        List<ImageEntity> images = new ArrayList();
        Iterator var3 = strings.iterator();

        while(var3.hasNext()) {
            String s = (String)var3.next();
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setFileName(s);
            images.add(imageEntity);
        }

        return images;
    }

    public String toString() {
        return "FlowerRequest{name='" + this.name + "', description='" + this.description + "', price=" + this.price + ", rating=" + this.rating + ", available=" + this.available + ", imageNames=" + this.imageNames + ", category=" + this.category + "}";
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

    public List<String> getImageNames() {
        return this.imageNames;
    }

    public Category getCategory() {
        return this.category;
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

    public void setImageNames(final List<String> imageNames) {
        this.imageNames = imageNames;
    }

    public void setCategory(final Category category) {
        this.category = category;
    }
}
