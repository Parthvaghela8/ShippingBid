package com.BidWheels.demo.Controller;

import com.BidWheels.demo.service.S3storage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RestController
@RequestMapping("/api/image")
@Slf4j
public class S3strorageController {

    private final S3storage s3storage;

    @Autowired
    public S3strorageController(S3storage s3storage) {
        this.s3storage = s3storage;
    }

    @PostMapping("/upload")
    public String uploadPhotoToImage(@RequestPart("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        log.info("Received request to upload photo to Profiles folder. File Name: {}, Content Type: {}, File Size: {} bytes",
                fileName, file.getContentType(), file.getSize());

        String fileUrl = s3storage.uploadPhotoToProfiles(file);

        if (fileUrl != null) {
            return "Photo uploaded successfully to Profiles folder. URL: " + fileUrl;
        } else {
            return "Failed to upload photo to Profiles folder.";
        }
    }


//    @PostMapping("/uploadPhotoToFoodPost")
//    public String uploadPhotoToFoodPost(@RequestPart("file") MultipartFile file) {
//        String fileName = file.getOriginalFilename();
//        log.info("Received request to upload photo to Profiles folder. File Name: {}, Content Type: {}, File Size: {} bytes",
//                fileName, file.getContentType(), file.getSize());
//
//        String fileUrl = s3storage.uploadPhotoToFoodPost(file);
//
//        if (fileUrl != null) {
//            return "Photo uploaded successfully to Profiles folder. URL: " + fileUrl;
//        } else {
//            return "Failed to upload photo to Profiles folder.";
//        }
//    }

    @GetMapping("/downloadFromProfiles")
    public ResponseEntity<InputStreamResource> downloadFromProfiles(@RequestParam String fileName) {
        log.info("Received request to download file '{}' from Profiles folder.", fileName);

        File file = s3storage.downloadFileFromImage(fileName);
        if (file != null) {
            try {
                InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                        .contentLength(file.length())
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .body(resource);
            } catch (FileNotFoundException e) {
                log.error("File '{}' not found in Profiles folder.", fileName);
                return ResponseEntity.notFound().build();
            }
        } else {
            log.error("Failed to download file '{}' from Profiles folder.", fileName);
            return ResponseEntity.notFound().build();
        }
    }

//    @GetMapping("/downloadFromFoodPost")
//    public ResponseEntity<InputStreamResource> downloadFromFoodPost(@RequestParam String fileName) {
//        log.info("Received request to download file '{}' from FoodPost folder.", fileName);
//
//        File file = s3storage.downloadFileFromFoodPost(fileName);
//        if (file != null) {
//            try {
//                InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
//                return ResponseEntity.ok()
//                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
//                        .contentLength(file.length())
//                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                        .body(resource);
//            } catch (FileNotFoundException e) {
//                log.error("File '{}' not found in FoodPost folder.", fileName);
//                return ResponseEntity.notFound().build();
//            }
//        } else {
//            log.error("Failed to download file '{}' from FoodPost folder.", fileName);
//            return ResponseEntity.notFound().build();
//        }
//    }
}
