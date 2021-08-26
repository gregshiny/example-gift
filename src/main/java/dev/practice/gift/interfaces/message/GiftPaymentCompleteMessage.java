package dev.practice.gift.interfaces.message;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class GiftPaymentCompleteMessage {
    private String orderToken;
}
