package dev.practice.gift.interfaces.message;


import dev.practice.gift.application.GiftFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GiftSqsMessageListener {
    private final GiftFacade giftFacade;

    @SqsListener(value = "order-payComplete-live.fifo", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void readMessage(GiftPaymentCompleteMessage message) {
        var orderToken = message.getOrderToken();
        log.info("[GiftSqsMessageListener.readMessage] orderToken = {}", orderToken);
        giftFacade.completePayment(orderToken);
    }
}
