package dev.practice.gift.infrastructure.gift;

import dev.practice.gift.common.exception.InvalidParamException;
import dev.practice.gift.domain.gift.Gift;
import dev.practice.gift.domain.gift.GiftStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GiftStoreImpl implements GiftStore {
    private final GiftRepository giftRepository;

    @Override
    public Gift store(Gift gift) {
        if (gift == null) throw new InvalidParamException();
        return giftRepository.save(gift);
    }
}
