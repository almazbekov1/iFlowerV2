package i.flowers.controller;

import i.flowers.database.dto.AboutUs;
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

    @GetMapping("/public/distance/{to}")
    public ResponseEntity<?> getDistance( @PathVariable String to){
        return new ResponseEntity<>(distanceMatrixService.callAndParse(to),HttpStatus.OK);
    }

    @PutMapping("/public/info/about-us")
    public ResponseEntity<?> aboutUs(@RequestBody AboutUs aboutUs){
        return new ResponseEntity<>(getAboutUs(saveInfoAboutUs(aboutUs)),HttpStatus.OK);
    }
    @GetMapping("/public/info/about-us")
    public ResponseEntity<?> aboutUs(){
        return new ResponseEntity<>(getAboutUs(infoRepository.getById(1l)),HttpStatus.OK);
    }


    private AboutUs getAboutUs(InfoEntity infoEntity){
        AboutUs aboutUs = new AboutUs();
        aboutUs.setDescription(infoEntity.getDescription());
        aboutUs.setImage(infoEntity.getImage());
        return aboutUs;
    }
    private InfoEntity saveInfoAboutUs(AboutUs aboutUs){
        InfoEntity infoEntity  = infoRepository.getById(1l);
        infoEntity.setDescription(aboutUs.getDescription());
        infoEntity.setImage(aboutUs.getImage());
        infoRepository.save(infoEntity);
        return infoEntity;
    }






}
