package i.flowers.database.dto;

import i.flowers.database.model.FlowerEntity;
import i.flowers.database.model.ImageEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Component
public class FlowerResponse {

    private Long id;
    private String name;
    private String description;
    private double price;
    private double rating;
    private boolean available;
    private List<String> image;

    public static FlowerResponse fromFLower(FlowerEntity flower){
        return new FlowerResponse(flower.getId(),flower.getName()
                ,flower.getDescription(),flower.getPrice()
                ,flower.getRating(),getImages(flower.getImages()),flower.isAvailable());
    }
    public List<FlowerResponse> fromFlower(List<FlowerEntity> flowerEntities){
        List<FlowerResponse> flowerResponses = new ArrayList<>();
        for (FlowerEntity f: flowerEntities) {
            flowerResponses.add(fromFLower(f));
        }
        return flowerResponses;
    }



    private static List<String> getImages(List<ImageEntity> images){
        List<String> strings = new ArrayList<>();
        for (ImageEntity i: images) {
            strings.add(i.getFileName());
        }
        return strings;
    }
    public FlowerResponse(Long id, String name, String description, double price, double rating, List<String> image,boolean available) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.image = image;
        this.available = available;
    }

    public FlowerResponse() {
    }
}
