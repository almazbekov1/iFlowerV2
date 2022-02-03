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
import org.springframework.stereotype.Component;

@Component
public class FlowerResponse {
    private Long id;
    private String name;
    private String description;
    private double price;
    private double rating;
    private boolean available;
    private List<String> image;
    private Category category;

    public static FlowerResponse fromFLower(FlowerEntity flower) {
        return new FlowerResponse(flower.getId(), flower.getName(), flower.getDescription(), flower.getPrice(), flower.getRating(), getImages(flower.getImages()), flower.isAvailable(), flower.getCategory());
    }

    public List<FlowerResponse> fromFlower(List<FlowerEntity> flowerEntities) {
        List<FlowerResponse> flowerResponses = new ArrayList();
        Iterator var3 = flowerEntities.iterator();

        while(var3.hasNext()) {
            FlowerEntity f = (FlowerEntity)var3.next();
            flowerResponses.add(fromFLower(f));
        }

        return flowerResponses;
    }

    private static List<String> getImages(List<ImageEntity> images) {
        List<String> strings = new ArrayList();
        Iterator var2 = images.iterator();

        while(var2.hasNext()) {
            ImageEntity i = (ImageEntity)var2.next();
            strings.add(i.getFileName());
        }

        return strings;
    }

    public FlowerResponse(Long id, String name, String description, double price, double rating, List<String> image, boolean available, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.image = image;
        this.available = available;
        this.category = category;
    }

    public FlowerResponse() {
    }

    public Long getId() {
        return this.id;
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

    public List<String> getImage() {
        return this.image;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setId(final Long id) {
        this.id = id;
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

    public void setImage(final List<String> image) {
        this.image = image;
    }

    public void setCategory(final Category category) {
        this.category = category;
    }
}
