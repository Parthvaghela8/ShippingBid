package com.BidWheels.demo.service;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
public class S3storage {

    private final AmazonS3 s3Client;

    public S3storage() {
        this.s3Client = AmazonS3ClientBuilder.defaultClient();
    }

    public String uploadPhotoImage(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String keyName = "image/" + fileName;
        return uploadPhoto(keyName, file);
    }


//    public String uploadPhotoToFoodPost( MultipartFile file) {
//        String fileName = file.getOriginalFilename();
//        String keyName = "FoodPost/" + fileName;
//        return uploadPhoto("unique-kindnesskettle-image", keyName, file);
//    }

    public File downloadFileFromImage(String fileName) {
        String keyName = "Profiles/" + fileName;
        return downloadFile("unique-kindnesskettle-image", keyName);
    }

//    public File downloadFileFromFoodPost(String fileName) {
//        String keyName = "FoodPost/" + fileName;
//        return downloadFile("unique-kindnesskettle-image", keyName);
//    }

    private String uploadPhoto(String keyName, MultipartFile file) {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentDisposition("inline");
            s3Client.putObject(new PutObjectRequest("s3instancepro", keyName, file.getInputStream(), metadata));

            // Construct and return the URL of the uploaded file
            return s3Client.getUrl("s3instancepro", keyName).toString();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    private File downloadFile(String bucketName, String keyName) {
        File tempFile = null;
        try {
            S3Object object = s3Client.getObject(new GetObjectRequest(bucketName, keyName));
            InputStream inputStream = object.getObjectContent();
            String[] keyParts = keyName.split("/");
            String fileName = keyParts[keyParts.length - 1]; // Extract the file name from the key
            tempFile = File.createTempFile("temp", getFileExtension(fileName)); // Use the file extension from the original file
            OutputStream outputStream = new FileOutputStream(tempFile);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            inputStream.close();
            outputStream.close();
        } catch (IOException | AmazonS3Exception e) {
            e.printStackTrace();
        }
        return tempFile;
    }

    // Method to extract file extension
    private String getFileExtension(String fileName) {
        int lastIndexOfDot = fileName.lastIndexOf('.');
        if (lastIndexOfDot == -1) {
            return ""; // No file extension found
        }
        return fileName.substring(lastIndexOfDot);
    }


}