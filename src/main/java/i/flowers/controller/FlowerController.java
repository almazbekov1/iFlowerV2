//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package i.flowers.controller;

import i.flowers.database.dto.FlowerRequest;
import i.flowers.database.dto.FlowerResponse;
import i.flowers.database.model.Category;
import i.flowers.database.service.FlowerService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api"})
@CrossOrigin(
        origins = {"*"},
        maxAge = 3600L
)
public class FlowerController {
    private final FlowerService flowerService;

    public FlowerController(FlowerService flowerService) {
        this.flowerService = flowerService;
    }

    @GetMapping({"/public/flowers"})
    public ResponseEntity<List<FlowerResponse>> getFlowers(@RequestParam int page, int size, @RequestParam(required = false) Category category, @RequestParam(required = false) Boolean available) {
        return new ResponseEntity(this.flowerService.getAll(page, size, category, available), HttpStatus.OK);
    }

    @GetMapping({"/public/flowers{id}"})
    public ResponseEntity<FlowerResponse> getById(@PathVariable("id") Long id) {
        FlowerResponse flower = this.flowerService.findById(id);
        return new ResponseEntity(flower, HttpStatus.OK);
    }

    @PostMapping({"/admin/flowers"})
    @CrossOrigin
    public ResponseEntity<FlowerResponse> save(@Valid @RequestBody FlowerRequest flower) {
        FlowerResponse result = this.flowerService.addNewFlower(flower);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PutMapping({"/admin/flowers/{id}"})
    @CrossOrigin
    public ResponseEntity<FlowerResponse> update(@Valid @RequestBody FlowerRequest flower, @PathVariable Long id) {
        FlowerResponse result = this.flowerService.updateFlower(flower, id);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @DeleteMapping({"/admin/flowers/{id}"})
    @CrossOrigin
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return new ResponseEntity(this.flowerService.delete(id), HttpStatus.OK);
    }

    @GetMapping({"/public/flowers/category"})
    public ResponseEntity<List<Category>> category() {
        return new ResponseEntity(List.of(Category.Bouquets, Category.InTheBox, Category.InTheBasket), HttpStatus.OK);
    }
}
