package com.guaneri.consumer.service.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.guaneri.consumer.service.FileDownloadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class FileDownloadServiceS3Impl implements FileDownloadService {

    private final AmazonS3 s3client;
    private final String bucketName;
    private final String downloadFolder;

    public FileDownloadServiceS3Impl(AmazonS3 s3client, @Value("${s3.bucket.name}")String bucketName, @Value("${s3.download.location}") String downloadFolder) {
        this.s3client = s3client;
        this.bucketName = bucketName;
        this.downloadFolder = downloadFolder;
    }

    @Override
    public void downloadFile(String objectId) {
        try {
            S3Object s3Object = s3client.getObject(bucketName, objectId);
            File file = new File(downloadFolder + "/" + objectId);
            file.createNewFile();
            s3Object.setObjectContent(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
