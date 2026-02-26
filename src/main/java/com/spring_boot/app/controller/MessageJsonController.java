package com.spring_boot.app.controller;


import com.spring_boot.app.dto.User;
import com.spring_boot.app.publisher.RabbitMQJsonProducer;
import org.apache.logging.log4j.message.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/messages/json")
public class MessageJsonController {
        // This controller can be used to handle JSON messages if needed
        // For example, you can add endpoints to publish or consume JSON messages

    public RabbitMQJsonProducer rabbitMQJsonProducer;

        public MessageJsonController(RabbitMQJsonProducer rabbitMQJsonProducer) {
            this.rabbitMQJsonProducer = rabbitMQJsonProducer;
        }

        //http://localhost:8082/api/v1/messages/json/publish
        @PostMapping("/publish")
         public ResponseEntity<String> sendJsonMessage(@RequestBody User user) {
            rabbitMQJsonProducer.sendJsonMessage(user);
            return ResponseEntity.ok("JSON Message sent to RabbitMQ....! ");
        }
}
