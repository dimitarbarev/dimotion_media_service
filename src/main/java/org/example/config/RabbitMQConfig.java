package org.example.config;

import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String BOARD_DELETE_QUEUE = "board.delete";

    public static final String USER_DELETE_QUEUE = "user.delete";

    @Bean
    public Queue boardDeleteQueue() {
        return new Queue(BOARD_DELETE_QUEUE, false);
    }

    @Bean
    public Queue userDeleteQueue() {
        return new Queue(USER_DELETE_QUEUE, false);
    }

    @Bean
    public TopicExchange mediaExchange() {
        return new TopicExchange("media.exchange");
    }

    @Bean
    public Binding boardDeleteBinding(Queue boardDeleteQueue, TopicExchange mediaExchange) {
        return BindingBuilder
                .bind(boardDeleteQueue)
                .to(mediaExchange)
                .with("board.deleted");
    }

    @Bean
    public Binding userDeleteBinding(Queue userDeleteQueue, TopicExchange mediaExchange) {
        return BindingBuilder
                .bind(userDeleteQueue)
                .to(mediaExchange)
                .with("user.deleted");
    }

    @PostConstruct
    public void confirmQueueInit() {
        System.out.println("✅ board.delete queue config initialized");
        System.out.println("✅ user.delete queue config initialized");
    }

}
