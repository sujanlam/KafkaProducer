package com.kafka.services;

import com.kafka.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisher {

    @Autowired
    private KafkaTemplate<String, Object> template;

    public void sendMessageToTopic(String message){
        CompletableFuture<SendResult<String, Object>> future = template.send("strange_topic", 4, null, message);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message to topic: " + message
                        + " with offset: ["+ result.getRecordMetadata().offset()
                        + " with partition: [" + result.getRecordMetadata().partition() +"]");
            }else{
                System.out.println("Unable to send message to topic: [" + message + "] due to " + ex.getMessage());
            }
        });

    }

    public void sendEventsToTopic(Customer customer){
        try{
            CompletableFuture<SendResult<String, Object>> future = template.send("sample_topic1", customer);
            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    System.out.println("Sent message to topic: " + customer.toString()
                            + " with offset: ["+ result.getRecordMetadata().offset()
                            + " with partition: [" + result.getRecordMetadata().partition() +"]");
                }else{
                    System.out.println("Unable to send message to topic: [" + customer.toString() + "] due to " + ex.getMessage());
                }
            });
        }catch (Exception ex){
            System.out.println("ERROR: "+ex.getMessage());
        }

    }
}
