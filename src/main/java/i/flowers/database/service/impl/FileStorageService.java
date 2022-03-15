package i.flowers.database.service.impl;

import java.io.IOException;
import java.util.stream.Stream;

import i.flowers.database.model.FileEntity;
import i.flowers.database.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {
    @Autowired
    private FileRepository fileRepository;

    public String upload(MultipartFile file) throws IOException {
        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileEntity FileDB = new FileEntity(file.getBytes(), filename);
        fileRepository.save(FileDB);
        return filename;
    }

    public FileEntity download(String name) {
        return fileRepository.findByName(name);
    }

    public void delete(String name) {
        fileRepository.deleteByName(name);
    }


    public Stream<FileEntity> getAllFiles() {
        return fileRepository.findAll().stream();
    }
}
