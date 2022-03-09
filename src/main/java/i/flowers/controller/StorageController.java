//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package i.flowers.controller;

import i.flowers.service.StorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping({"/api"})
@CrossOrigin(
        origins = {"*"},
        maxAge = 3600L
)
public class StorageController {
    private final StorageService storageService;

    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping({"/admin/files"})
    @CrossOrigin
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        return new ResponseEntity(this.storageService.uploadFile(file), HttpStatus.OK);
    }

    @GetMapping({"/public/files/{fileName}"})
    @CrossOrigin
    public ResponseEntity<byte[]> downloadFiles(@PathVariable String fileName) {
        byte[] data = this.storageService.downloadFile(fileName);
        return ((BodyBuilder)ResponseEntity.ok().header("Content-Disposition", new String[]{"attachment; filename=" + fileName})).body(data);
    }

    @DeleteMapping({"/admin/files/{fileName}"})
    @CrossOrigin
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
        return new ResponseEntity(this.storageService.deleteFile(fileName), HttpStatus.OK);
    }
}
