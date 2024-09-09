package com.market.secondhandmarketplace.application.dto.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;


@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OpenWeatherResponse {
    private Coord coord;
    private List<Weather> weather;
    private String base;
    private Main main;
    private Integer visibility;
    private Wind wind;
    private Clouds clouds;
    private Long dt;
    private Sys sys;
    private Long timezone; // `timeZone` -> `timezone`
    private Long id;
    private String name;
    private Integer cod;

    @Data
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Coord {
        private Double lon;
        private Double lat;
    }

    @Data
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class Weather {
        private int id;
        private String main;
        private String description;
        private String icon;
    }

    @Data
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Main {
        private Double temp;
        @JsonProperty("feels_like")
        private Double feelsLike;
        @JsonProperty("temp_min")
        private Double tempMin;
        @JsonProperty("temp_max")
        private Double tempMax;
        private Integer pressure;
        private Integer humidity;
        @JsonProperty("grnd_level")
        private Integer grndLevel; // `grnd_level` 필드 이름 수정
        @JsonProperty("sea_level")
        private Integer seaLevel;  // `sea_level` 필드 이름 수정
    }

    @Data
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Wind {
        private Double speed;
        private Integer deg;
        private Double gust; // `gust` 필드 추가
    }

    @Data
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Clouds {
        private Integer all;
    }

    @Data
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Sys {
        private Integer type;
        private Integer id;
        private String country;
        private Long sunrise;
        private Long sunset;
    }
}
