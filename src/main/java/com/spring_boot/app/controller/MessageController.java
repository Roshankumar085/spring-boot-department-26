package com.spring_boot.app.controller;


import com.spring_boot.app.publisher.RabbitMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {

    private RabbitMQProducer rabbitMQProducer;

    // Accept null when RabbitMQProducer bean is not created (rabbitmq.enabled=false)
    public MessageController(@Autowired(required = false)
                             RabbitMQProducer rabbitMQProducer) {
        this.rabbitMQProducer = rabbitMQProducer;
    }

    //http://localhost:8082/api/v1/messages/publish?message=HelloWorld
    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message) {
        rabbitMQProducer.sendMessage(message);
        return ResponseEntity.ok("Message sent to RabbitMQ....! ");

    }
}
