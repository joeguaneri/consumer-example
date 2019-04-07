package com.guaneri.consumer.service.sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.google.gson.Gson;
import com.guaneri.consumer.model.QueueMessage;
import com.guaneri.consumer.service.QueueService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QueueServiceSQSImpl implements QueueService {

    private final AmazonSQS client;
    private final String queueUrl;
    private final Gson gson;

    public QueueServiceSQSImpl(AmazonSQS client, @Value("${sqs.queue.url}") String queueUrl) {
        this.client = client;
        this.queueUrl = queueUrl;
        this.gson = new Gson();
    }

    @Override
    public List<String> getObjectIds() {
        List<Message> messageList = client.receiveMessage(queueUrl).getMessages();
        List<String> objectIdList = new ArrayList<>();
        messageList.stream().forEach(message -> {
            objectIdList.add(gson.fromJson(message.getBody(), QueueMessage.class).getFileMeta().getFileId());
            client.deleteMessage(queueUrl, message.getReceiptHandle());
        });
        return objectIdList;
    }

}
