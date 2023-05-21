package com.tnh.authservice.config;

import com.tnh.authservice.messaging.sender.UserSender;
import com.tnh.authservice.messaging.sender.VotingSender;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("com.tnh.authservice.fanout");
    }

    @Bean
    public FanoutExchange votingExchange() {
        return new FanoutExchange("com.tnh.authservicevoting.fanout");
    }

    @Bean
    public FanoutExchange implFriendVotingExchange() {
        return new FanoutExchange("com.tnh.friendchatservice.fanout.voting");
    }


    @Bean
    public FanoutExchange implGroupVotingExchange() {
        return new FanoutExchange("com.tnh.groupchatservice.fanout.voting");
    }

    @Bean
    public UserSender userSender(RabbitTemplate rabbitTemplate, FanoutExchange fanoutExchange) {
        return new UserSender(rabbitTemplate, fanoutExchange);
    }

    @Bean
    public VotingSender votingSender(RabbitTemplate rabbitTemplate, FanoutExchange votingExchange) {
        return new VotingSender(rabbitTemplate, votingExchange);
    }

    @Bean
    public Queue compensateCreateUserFromFriendQueue() {
        return new Queue("com.tnh.authservice.compensateFromFriend");
    }
    @Bean
    public Queue compensateCreateUserFromGroupQueue() {
        return new Queue("com.tnh.authservice.compensateFromGroup");
    }

    @Bean
    public Binding compensateCreateFromFriendUser(FanoutExchange implFriendVotingExchange, Queue compensateCreateUserFromFriendQueue) {
        return BindingBuilder.bind(compensateCreateUserFromFriendQueue).to(implFriendVotingExchange);
    }

    @Bean
    public Binding compensateCreateFromGroupUser(FanoutExchange implGroupVotingExchange, Queue compensateCreateUserFromGroupQueue) {
        return BindingBuilder.bind(compensateCreateUserFromGroupQueue).to(implGroupVotingExchange);
    }

    @Bean("Jackson2JsonMessageConverter")
    Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory, Jackson2JsonMessageConverter jackson2JsonMessageConverter) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter);
        return rabbitTemplate;
    }


}
