package com.guaneri.consumer.service.impl;

import com.guaneri.consumer.service.ConsumeService;
import com.guaneri.consumer.service.FileDownloadService;
import com.guaneri.consumer.service.QueueService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumeServiceImpl implements ConsumeService {

    private final QueueService queueService;
    private final FileDownloadService fileDownloadService;

    public ConsumeServiceImpl(QueueService queueService, FileDownloadService fileDownloadService) {
        this.queueService = queueService;
        this.fileDownloadService = fileDownloadService;
    }

    @Scheduled(fixedDelay = 1000)
    @Override
    public void consumeFiles() {
        System.out.println("Checking for files...");
        List<String> objectIds = queueService.getObjectIds();
        System.out.println("Found " + objectIds.size() + " files to download.");
        objectIds.stream().forEach(objectId -> {
            System.out.println("Downloading file for ID: " + objectId);
            fileDownloadService.downloadFile(objectId);
            System.out.println("File download complete.");
        });
    }
}
