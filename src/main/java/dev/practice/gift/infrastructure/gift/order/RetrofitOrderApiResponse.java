package dev.practice.gift.infrastructure.gift.order;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class RetrofitOrderApiResponse {

    @Getter
    @Builder
    @ToString
    public static class Register {
        private final String orderToken;
    }
}
