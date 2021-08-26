package dev.practice.gift.domain.gift;

import dev.practice.gift.domain.gift.order.OrderApiCaller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GiftServiceImpl implements GiftService {
    private final GiftReader giftReader;
    private final GiftStore giftStore;
    private final OrderApiCaller orderApiCaller;
    private final GiftToOrderMapper giftToOrderMapper;

    /**
     * 선물 주문 정보를 가져온다
     * 선물 수령자의 수락 페이지 로딩 시에 사용된다
     *
     * @param giftToken
     * @return
     */
    @Override
    public GiftInfo getGiftInfo(String giftToken) {
        var gift = giftReader.getGiftBy(giftToken);
        return new GiftInfo(gift);
    }

    /**
     * 선물하기 주문을 등록한다
     * 해당 주문을 주문 서비스에 등록하기 위해 API 를 호출하고
     * 등록된 주문의 식별키와 선물 관련 정보를 반영하여 Gift 도메인을 저장한다
     *
     * @param request
     * @return
     */
    @Override
    @Transactional
    public GiftInfo registerOrder(GiftCommand.Register request) {
        var orderCommand = giftToOrderMapper.of(request);
        var orderToken = orderApiCaller.registerGiftOrder(orderCommand);
        var initGift = request.toEntity(orderToken);
        var gift = giftStore.store(initGift);
        return new GiftInfo(gift);
    }

    /**
     * 선물하기 주문의 상태를 [결제중] 으로 변경한다
     *
     * @param giftToken
     */
    @Override
    @Transactional
    public void requestPaymentProcessing(String giftToken) {
        var gift = giftReader.getGiftBy(giftToken);
        gift.inPayment();
    }

    /**
     * 주문 서비스에서 결제 완료 후 orderToken 을 메시징으로 발행하면
     * 선물하기 서비스에서 이를 읽어서 선물 주문의 결제를 완료 상태로 변경한다
     *
     * @param orderToken
     */
    @Override
    @Transactional
    public void completePayment(String orderToken) {
        var gift = giftReader.getGiftByOrderToken(orderToken);
        gift.completePayment();
    }

    /**
     * 선물 수령자가 배송지를 입력하고 [선물 수락] 하면
     * 선물 상태를 Accept 로 변경하고, 주문 서비스 API 를 호출하여 주문의 배송지 주소를 업데이트 한다
     *
     * @param request
     */
    @Override
    @Transactional
    public void acceptGift(GiftCommand.Accept request) {
        var giftToken = request.getGiftToken();
        var gift = giftReader.getGiftBy(giftToken);
        gift.accept(request);

        orderApiCaller.updateReceiverInfo(gift.getOrderToken(), request);
    }
}
