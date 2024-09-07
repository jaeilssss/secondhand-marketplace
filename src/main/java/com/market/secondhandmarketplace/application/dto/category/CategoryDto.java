package com.market.secondhandmarketplace.application.dto.category;

import com.market.secondhandmarketplace.domain.entity.category.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class CategoryDto {

    @Getter @Setter
    @Builder
    public static class RegisterCategory {
        private String name;
        private Long parentId = -1L;
        private int depth = 0;

        public Category toEntity(Category parent) {
            return Category.builder()
                    .name(name)
                    .parent(parent)
                    .depth(parent.getDepth()+1)
                    .build();
        }
        public Category toEntity() {
            return Category.builder()
                    .name(name)
                    .depth(depth)
                    .build();
        }
    }

    @Getter @Setter
    @Builder
    public static class CategoryResponse {
        private String name;
        private Long id;
        private List<CategoryResponse> children;
    }
}
