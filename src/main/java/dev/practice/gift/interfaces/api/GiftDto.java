package dev.practice.gift.interfaces.api;

import dev.practice.gift.domain.gift.GiftInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class GiftDto {

    @Getter
    @Setter
    @ToString
    public static class RegisterReq {
        @NotNull(message = "userId 는 필수값입니다")
        private Long buyerUserId;

        @NotBlank(message = "payMethod 는 필수값입니다")
        private String payMethod;

        @NotBlank(message = "pushType 는 필수값입니다")
        private String pushType;

        @NotBlank(message = "giftReceiverName 는 필수값입니다")
        private String giftReceiverName;

        @NotBlank(message = "giftReceiverPhone 는 필수값입니다")
        private String giftReceiverPhone;

        @NotBlank(message = "giftMessage 는 필수값입니다")
        private String giftMessage;

        private List<RegisterOrderItemReq> orderItemList;
    }

    @Getter
    @Setter
    @ToString
    public static class RegisterOrderItemReq {
        @NotNull(message = "orderCount 는 필수값입니다")
        private Integer orderCount;

        @NotBlank(message = "itemToken 는 필수값입니다")
        private String itemToken;

        @NotBlank(message = "itemName 는 필수값입니다")
        private String itemName;

        @NotNull(message = "itemPrice 는 필수값입니다")
        private Long itemPrice;

        private List<RegisterOrderItemOptionGroupReq> orderItemOptionGroupList;
    }

    @Getter
    @Setter
    @ToString
    public static class RegisterOrderItemOptionGroupReq {
        @NotNull(message = "ordering 는 필수값입니다")
        private Integer ordering;

        @NotBlank(message = "itemOptionGroupName 는 필수값입니다")
        private String itemOptionGroupName;

        private List<RegisterOrderItemOptionReq> orderItemOptionList;
    }

    @Getter
    @Setter
    @ToString
    public static class RegisterOrderItemOptionReq {
        @NotNull(message = "ordering 는 필수값입니다")
        private Integer ordering;

        @NotBlank(message = "itemOptionName 는 필수값입니다")
        private String itemOptionName;

        @NotNull(message = "itemOptionPrice 는 필수값입니다")
        private Long itemOptionPrice;
    }

    @Getter
    public static class RegisterRes {
        private final String orderToken;
        private final String giftToken;

        public RegisterRes(GiftInfo giftInfo) {
            this.orderToken = giftInfo.getOrderToken();
            this.giftToken = giftInfo.getGiftToken();
        }
    }

    @Getter
    @Setter
    @ToString
    public static class CompletePaymentReq {
        private String orderToken;
    }

    @Getter
    @Setter
    @ToString
    public static class AcceptGiftReq {
        @NotBlank(message = "receiverName 는 필수값입니다")
        private String receiverName;

        @NotBlank(message = "receiverPhone 는 필수값입니다")
        private String receiverPhone;

        @NotBlank(message = "receiverZipcode 는 필수값입니다")
        private String receiverZipcode;

        @NotBlank(message = "receiverAddress1 는 필수값입니다")
        private String receiverAddress1;

        @NotBlank(message = "receiverAddress2 는 필수값입니다")
        private String receiverAddress2;

        @NotBlank(message = "etcMessage 는 필수값입니다")
        private String etcMessage;
    }
}
