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
    public String uploadPhoto(@RequestPart("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        log.info("Received request to upload photo to image folder. File Name: {}, Content Type: {}, File Size: {} bytes",
                fileName, file.getContentType(), file.getSize());

        String fileUrl = s3storage.uploadPhotoToShippments(file);

        if (fileUrl != null) {
            return fileUrl;
        } else {
            return "Failed to upload photo to image folder.";
        }
    }

}
