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
public class FlowerRequest {


    private String name;
    private String description;
    private double price;
    private double rating;
    private boolean available;
    private List<String> imageNames;

    public FlowerEntity toFlower(){
        return new FlowerEntity(name,description,price,rating,available,getImages(imageNames));
    }
    private List<ImageEntity> getImages(List<String> strings){
        List<ImageEntity> images = new ArrayList<>();
        for (String s:strings) {
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setFileName(s);
            images.add(imageEntity);
        }
        return images;
    }

    @Override
    public String toString() {
        return "FlowerRequest{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                ", available=" + available +
                ", imageNames=" + imageNames +
                '}';
    }
}
