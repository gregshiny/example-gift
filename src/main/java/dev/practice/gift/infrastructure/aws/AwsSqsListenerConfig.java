package dev.practice.gift.infrastructure.aws;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.aws.messaging.config.QueueMessageHandlerFactory;
import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory;
import org.springframework.cloud.aws.messaging.listener.QueueMessageHandler;
import org.springframework.cloud.aws.messaging.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.PayloadMethodArgumentResolver;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Slf4j
@Component
public class AwsSqsListenerConfig {

    @Bean
    public SimpleMessageListenerContainerFactory simpleMessageListenerContainerFactory(AmazonSQSAsync amazonSQSAsync) {
        SimpleMessageListenerContainerFactory factory = new SimpleMessageListenerContainerFactory();
        factory.setAmazonSqs(amazonSQSAsync);
        factory.setAutoStartup(true);
        return factory;
    }

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(
            SimpleMessageListenerContainerFactory simpleMessageListenerContainerFactory,
            QueueMessageHandler queueMessageHandler,
            ThreadPoolTaskExecutor messageThreadPoolTaskExecutor
    ) {
        SimpleMessageListenerContainer container = simpleMessageListenerContainerFactory.createSimpleMessageListenerContainer();
        container.setMessageHandler(queueMessageHandler);
        container.setTaskExecutor(messageThreadPoolTaskExecutor);
        return container;
    }

    @Bean
    @ConditionalOnMissingBean(QueueMessageHandlerFactory.class)
    public QueueMessageHandlerFactory queueMessageHandlerFactory(AmazonSQSAsync amazonSQSAsync) {
        QueueMessageHandlerFactory factory = new QueueMessageHandlerFactory();
        factory.setAmazonSqs(amazonSQSAsync);

        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
        messageConverter.setStrictContentTypeMatch(false);
        factory.setArgumentResolvers(Collections.singletonList(new PayloadMethodArgumentResolver(messageConverter)));
        return factory;
    }

    @Bean
    @ConditionalOnMissingBean(QueueMessageHandler.class)
    public QueueMessageHandler queueMessageHandler(QueueMessageHandlerFactory queueMessageHandlerFactory) {
        return queueMessageHandlerFactory.createQueueMessageHandler();
    }

    @Bean
    public ThreadPoolTaskExecutor messageThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setThreadNamePrefix("sqs-");
        taskExecutor.setCorePoolSize(8);
        taskExecutor.setMaxPoolSize(100);
        taskExecutor.afterPropertiesSet();
        return taskExecutor;
    }
}
