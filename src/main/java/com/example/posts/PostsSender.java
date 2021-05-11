package com.example.posts;

import com.example.messaging.MessagingConfig;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PostsSender {
    private final RabbitTemplate rabbitTemplate;

    public void add(Post post) {
        this.rabbitTemplate.convertAndSend(MessagingConfig.POSTS_EXCHANGE_NAME, MessagingConfig.ADD_POST_ROUTING_KEY, makeMessage(post));
    }

    private static Message makeMessage(Post post) {
        return null;
    }
}
