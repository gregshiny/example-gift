package dev.practice.gift.infrastructure.gift;

import dev.practice.gift.domain.gift.Gift;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GiftRepository extends JpaRepository<Gift, Long> {
    Optional<Gift> findByGiftToken(String giftToken);
    Optional<Gift> findByOrderToken(String orderToken);
}
