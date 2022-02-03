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

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class FlowerResponse {
    private Long id;
    private String name;
    private String description;
    private double price;
    private double rating;
    private boolean available;
    private boolean block;
    private List<String> image;
    private Category category;

    public static FlowerResponse fromFLower(FlowerEntity flower) {
        return new FlowerResponse(flower.getId(), flower.getName(), flower.getDescription(), flower.getPrice()
                , flower.getRating(), getImages(flower.getImages()), flower.isAvailable(), flower.getCategory(),flower.isBlock());
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

    public FlowerResponse(Long id, String name, String description, double price
            , double rating, List<String> image, boolean available, Category category,Boolean isBlock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.image = image;
        this.available = available;
        this.category = category;
        this.block = isBlock;
    }

    public FlowerResponse() {
    }
}
