package dev.practice.gift.infrastructure.gift.order;

import dev.practice.gift.common.response.CommonResponse;
import dev.practice.gift.domain.gift.GiftCommand;
import dev.practice.gift.domain.gift.order.OrderApiCommand;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitOrderApi {

    @POST("api/v1/gift-orders/init")
    Call<CommonResponse<RetrofitOrderApiResponse.Register>> registerOrder(@Body OrderApiCommand.Register request);

    @POST("api/v1/gift-orders/{orderToken}/update-receiver-info")
    Call<Void> updateReceiverInfo(@Path("orderToken") String orderToken, @Body GiftCommand.Accept request);
}
