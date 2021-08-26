package dev.practice.gift.infrastructure.retrofit;

import dev.practice.gift.infrastructure.gift.order.RetrofitOrderApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RetrofitServiceRegistry {

    @Value("${example.order.base-url}")
    private String baseUrl;

    @Bean
    public RetrofitOrderApi retrofitOrderApi() {
        var retrofit = RetrofitUtils.initRetrofit(baseUrl);
        return retrofit.create(RetrofitOrderApi.class);
    }
}
