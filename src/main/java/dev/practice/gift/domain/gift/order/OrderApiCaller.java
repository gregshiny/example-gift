package dev.practice.gift.domain.gift.order;

import dev.practice.gift.domain.gift.GiftCommand;

public interface OrderApiCaller {
    String registerGiftOrder(OrderApiCommand.Register request);

    void updateReceiverInfo(String orderToken, GiftCommand.Accept request);
}
