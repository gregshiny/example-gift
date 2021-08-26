package dev.practice.gift.domain.gift;

import dev.practice.gift.domain.gift.order.OrderApiCommand;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

public class GiftCommand {

    @Getter
    @Builder
    @ToString
    public static class Register {
        private final Long buyerUserId;
        private final String payMethod;
        private final String pushType;
        private final String giftReceiverName;
        private final String giftReceiverPhone;
        private final String giftMessage;
        private final List<RegisterOrderItem> orderItemList;

        public Gift toEntity(String orderToken) {
            return Gift.builder()
                    .buyerUserId(buyerUserId)
                    .orderToken(orderToken)
                    .pushType(Gift.PushType.valueOf(pushType))
                    .giftReceiverName(giftReceiverName)
                    .giftReceiverPhone(giftReceiverPhone)
                    .giftMessage(giftMessage)
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    public static class RegisterOrderItem {
        private final String orderCount;
        private final String itemToken;
        private final String itemName;
        private final String itemPrice;
        private final List<RegisterOrderItemOptionGroup> orderItemOptionGroupList;
    }

    @Getter
    @Builder
    @ToString
    public static class RegisterOrderItemOptionGroup {
        private final Integer ordering;
        private final String itemOptionGroupName;
        private final List<OrderApiCommand.RegisterOrderItemOption> orderItemOptionList;
    }

    @Getter
    @Builder
    @ToString
    public static class RegisterOrderItemOption {
        private final Integer ordering;
        private final String itemOptionName;
        private final Long itemOptionPrice;
    }

    @Getter
    @Builder
    @ToString
    public static class Accept {
        private final String giftToken;
        private final String receiverName;
        private final String receiverPhone;
        private final String receiverZipcode;
        private final String receiverAddress1;
        private final String receiverAddress2;
        private final String etcMessage;
    }
}
