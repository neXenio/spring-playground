package com.example.messaging;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {
    public static final String POSTS_EXCHANGE_NAME = "posts";
    public static final String ADD_POST_QUEUE_NAME = "add-post";
    public static final String ADD_POST_ROUTING_KEY = "add-post";

    private static final boolean NON_DURABLE = false;
    private static final boolean DO_NOT_AUTO_DELETE = false;

    @Bean
    public Declarables topicBindings() {
        final var addPostQueue = new Queue(ADD_POST_QUEUE_NAME, NON_DURABLE);
        final var postsExchange = new TopicExchange(POSTS_EXCHANGE_NAME, NON_DURABLE, DO_NOT_AUTO_DELETE);

        return new Declarables(
            addPostQueue,
            postsExchange,
            BindingBuilder.bind(addPostQueue).to(postsExchange).with(ADD_POST_ROUTING_KEY)
        );
    }
}
