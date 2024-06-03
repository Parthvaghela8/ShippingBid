package com.BidWheels.demo.service;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@Service
public class S3storage {

    private final AmazonS3 s3Client;

    public S3storage() {
        this.s3Client = AmazonS3ClientBuilder.defaultClient();
    }

    public String uploadPhotoToShippments(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;
        String keyName = "image/" + uniqueFileName;
        return uploadPhoto("s3instancepro",keyName, file);
    }

    private String uploadPhoto(String bucketName, String keyName, MultipartFile file) {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentDisposition("inline");
            s3Client.putObject(new PutObjectRequest(bucketName, keyName, file.getInputStream(), metadata));

            // Construct and return the URL of the uploaded file
            String fileUrl = s3Client.getUrl(bucketName, keyName).toString();
            return fileUrl;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}