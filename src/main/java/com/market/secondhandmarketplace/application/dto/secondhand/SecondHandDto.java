package com.market.secondhandmarketplace.application.dto.secondhand;

import com.market.secondhandmarketplace.domain.entity.category.Category;
import com.market.secondhandmarketplace.domain.entity.image.Image;
import com.market.secondhandmarketplace.domain.entity.member.Member;
import com.market.secondhandmarketplace.domain.entity.secondhand.SecondHand;
import com.market.secondhandmarketplace.globals.factory.GeometryPoint;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

public class SecondHandDto {

    @Getter @Setter
    @Builder
    public static class PostSecondHand {
        private String name;
        private Long categoryId;
        private int price;
        private Long memberId;
        private String description;
        private double latitude;
        private double longitude;
        private List<String> imageUrlList;

        public SecondHand toEntity(Category category, Member member) {
            List<Image> imageList = imageUrlList.stream()
                    .map(it->Image.builder().url(it).build()).toList();
            return SecondHand.builder()
                    .name(name)
                    .category(category)
                    .member(member)
                    .price(price)
                    .description(description)
                    .imageUrlList(imageList)
                    .location(GeometryPoint.createPoint(latitude, longitude))
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
    }

    @Getter @Setter
    @Builder
    public static class SecondHandResponse {
        private Long id;
        private String name;
        private int price;
        private String postMemberName;
        private String categoryName;
        private String description;
        private List<String> imageUrlList;
    }
}
