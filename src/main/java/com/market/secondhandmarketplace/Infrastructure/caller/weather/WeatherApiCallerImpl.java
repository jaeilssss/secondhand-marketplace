package com.market.secondhandmarketplace.Infrastructure.caller.weather;

import com.market.secondhandmarketplace.application.dto.weather.OpenWeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class WeatherApiCallerImpl implements WeatherApiCaller{
    private final WebClient.Builder webClient;

    @Value("${api.openWeatherApiKey}")
    private String apiKey;
    @Value("${api.openWeatherBaseUrl}")
    private String openWeatherUrl;
    @Override
    public OpenWeatherResponse getWeatherData(Double lat, Double lon) {
        WebClient client = webClient
                .baseUrl(openWeatherUrl)
                .build();
        OpenWeatherResponse response = client.get().uri(
                        uriBuilder -> uriBuilder.queryParam("lat",lat)
                                .queryParam("lon",lon)
                                .queryParam("appid",apiKey)
                                .queryParam("units","metric")
                                .queryParam("lang","kr")
                                .build())
                .retrieve().bodyToMono(OpenWeatherResponse.class).block();
        return response;
    }
}
