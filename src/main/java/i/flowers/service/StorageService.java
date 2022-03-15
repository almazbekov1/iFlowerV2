package i.flowers.service;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import i.flowers.database.model.FileEntity;
import i.flowers.database.service.impl.FileStorageService;
import i.flowers.exception.ErrorMessage;
import i.flowers.exception.UserServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


@Service
@Slf4j
@RequiredArgsConstructor
public class StorageService {

    @Value("${application.bucket.name}")
    private String bucketName;

    private final FileStorageService fileStorageService;

    @Autowired
    private AmazonS3 s3Client;


    public String uploadFile(MultipartFile file) {
//        File fileObj = convertMultiPartFileToFile(file);
        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
//        s3Client.putObject(new PutObjectRequest(bucketName, filename, fileObj));
        try {
//            FileEntity fileEntity = new FileEntity(file.getBytes(),filename);
            return fileStorageService.upload(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new UserServiceException("file exception");
        }

//        fileObj.delete();
//        return filename;
    }

    public byte[] downloadFile(String fileName) {
//        S3Object s3Object = s3Client.getObject(bucketName, fileName);
//        S3ObjectInputStream inputStream = s3Object.getObjectContent();
//        try {
//            byte[] content = IOUtils.toByteArray(inputStream);
            return fileStorageService.download(fileName).getData();
//            return content;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
    }

    public String deleteFile(String fileName) {
//        s3Client.deleteObject(bucketName, fileName);
        fileStorageService.delete(fileName);
        return fileName + " remove...";
    }

    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Erorr converting multipartFile to file ", e);
        }
        return convertedFile;
    }

}


