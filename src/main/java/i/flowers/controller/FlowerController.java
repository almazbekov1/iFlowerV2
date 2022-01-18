package i.flowers.controller;

import i.flowers.database.dto.FlowerRequest;
import i.flowers.database.dto.FlowerResponse;
import i.flowers.database.model.FlowerEntity;
import i.flowers.database.service.FlowerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping(value = "/api/flowers")
@CrossOrigin
//@RolesAllowed("admin")
public class FlowerController {
    private final FlowerService flowerService;

    public FlowerController(FlowerService flowerService) {
        this.flowerService = flowerService;
    }

    @GetMapping()
    public ResponseEntity<List<FlowerResponse>> getFlowers(){
        return new ResponseEntity<>(flowerService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlowerResponse> getById(@PathVariable("id") Long id){
        FlowerResponse flower = flowerService.findById(id);
        return new ResponseEntity<>(flower,HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<FlowerResponse>save(@RequestBody FlowerRequest flower){
        FlowerResponse result = flowerService.addNewFlower(flower);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<FlowerResponse>update(@RequestBody FlowerRequest flower,@PathVariable Long id){

        FlowerResponse result = flowerService.updateFlower(flower,id);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

}
