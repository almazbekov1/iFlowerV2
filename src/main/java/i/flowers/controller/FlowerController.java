package i.flowers.controller;

import i.flowers.database.model.FlowerEntity;
import i.flowers.database.service.FlowerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/flowers")
public class FlowerController {
    private final FlowerService flowerService;

    public FlowerController(FlowerService flowerService) {
        this.flowerService = flowerService;
    }

    @GetMapping()
    public ResponseEntity<List<FlowerEntity>> getFlowers(){
        return new ResponseEntity<>(flowerService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlowerEntity> getById(@PathVariable("id") Long id){
        FlowerEntity flower = flowerService.findById(id);
        return new ResponseEntity<>(flower,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FlowerEntity>save(@RequestBody FlowerEntity flower){
        FlowerEntity result = flowerService.addNewFlower(flower);
        return new ResponseEntity<>(result,HttpStatus.OK);

    }
}
