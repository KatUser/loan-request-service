package ru.loans.loan_request_service.configuration.rabbit;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String QUEUE_NAME = "loan-request-queue";
    public static final String EXCHANGE_NAME = "loan-request-exchange";
    public static final String ROUTING_KEY = "loan-request";

    public static final String DEAD_QUEUE = "dead-loan-request-queue";
    public static final String DEAD_EXCHANGE = "dead-loan-request-exchange";
    public static final String DEAD_ROUTING_KEY = "dead-loan-request";

    @Bean
    public Queue taskQueue() {
        return QueueBuilder.durable(QUEUE_NAME)
                .withArgument("x-dead-letter-exchange", DEAD_EXCHANGE)
                .withArgument("x-dead-letter-routing-key", DEAD_ROUTING_KEY)
                .build();
    }

    @Bean
    public TopicExchange taskExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(Queue taskQueue, TopicExchange taskExchange) {
        return BindingBuilder.bind(taskQueue)
                .to(taskExchange)
                .with(ROUTING_KEY);
    }

    @Bean
    public DirectExchange deadExchange() {
        return new DirectExchange(DEAD_EXCHANGE);
    }

    @Bean
    public Queue deadQueue() {
        return QueueBuilder.durable(DEAD_QUEUE).build();
    }

    @Bean
    public Binding deadBinding() {
        return BindingBuilder.bind(deadQueue())
                .to(deadExchange())
                .with(DEAD_ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }
}