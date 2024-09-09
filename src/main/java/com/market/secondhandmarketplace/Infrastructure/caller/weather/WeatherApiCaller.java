package com.market.secondhandmarketplace.Infrastructure.caller.weather;

import com.market.secondhandmarketplace.application.dto.weather.OpenWeatherResponse;

public interface WeatherApiCaller {

    public OpenWeatherResponse getWeatherData(Double lat, Double lon);

}
