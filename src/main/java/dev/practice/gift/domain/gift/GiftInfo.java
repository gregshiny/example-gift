package dev.practice.gift.domain.gift;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GiftInfo {
    private final String orderToken;
    private final String giftToken;
    private final Gift.PushType pushType;
    private final String giftReceiverName;
    private final String giftReceiverPhone;
    private final String giftMessage;

    public GiftInfo(Gift gift) {
        this.orderToken = gift.getOrderToken();
        this.giftToken = gift.getGiftToken();
        this.pushType = gift.getPushType();
        this.giftReceiverName = gift.getGiftReceiverName();
        this.giftReceiverPhone = gift.getGiftReceiverName();
        this.giftMessage = gift.getGiftMessage();
    }
}
