package com.market.secondhandmarketplace.presentation.request.secondhand;

import com.market.secondhandmarketplace.application.dto.secondhand.SecondHandDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class SecondHandRequest {

    @Getter @Setter
    @Builder
    public static class PostSecondHand {
        @NotBlank(message = "상품 이름을 입력해주세요")
        private String name;
        @NotNull
        private Long categoryId;
        @NotNull
        private int price;
        @NotNull
        private Long memberId;
        @NotBlank(message = "상품 설명을 입력해주세요")
        private String description;
        private List<String> imageUrlList;
        private double latitude;
        private double longitude;

        public SecondHandDto.PostSecondHand toDto() {
            return SecondHandDto.PostSecondHand.builder()
                    .name(name)
                    .price(price)
                    .description(description)
                    .latitude(latitude)
                    .longitude(longitude)
                    .imageUrlList(imageUrlList)
                    .build();
        }
    }

    @Getter @Setter
    @Builder
    public static class ModifySecondHand {
        private String name;
        private int price;
        private String description;
        private List<String> imageUrlList;

        public SecondHandDto.ModifySecondHand toDto() {
            return SecondHandDto.ModifySecondHand.builder()
                    .name(name)
                    .price(price)
                    .description(description)
                    .imageUrlList(imageUrlList)
                    .build();
        }
    }
}
