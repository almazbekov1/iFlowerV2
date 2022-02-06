package i.flowers.controller;

import i.flowers.database.model.InfoEntity;
import i.flowers.database.repository.InfoRepository;
import i.flowers.service.DistanceMatrixService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api"})
@CrossOrigin(
        origins = {"*"},
        maxAge = 3600L
)
@AllArgsConstructor
public class InfoController {


    private final InfoRepository infoRepository;
    private final DistanceMatrixService distanceMatrixService;

    @GetMapping("/public/info")
    public ResponseEntity<?> getInfo(){
        return new ResponseEntity<>(infoRepository.getById(1l), HttpStatus.OK);

    }
    @PutMapping("/public/info")
    public ResponseEntity<?> updateInfo(@RequestBody InfoEntity info){
        info.setId(1l);
        return new ResponseEntity<>(infoRepository.save(info), HttpStatus.OK);
    }

    @GetMapping("/public/distance")
    public ResponseEntity<?> getDistance(@RequestParam String from,@RequestParam String to){
        return new ResponseEntity<>(distanceMatrixService.callAndParse(from,to).toString(),HttpStatus.OK);
    }




}
