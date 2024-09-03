package com.market.secondhandmarketplace.presentation.request.category;

import com.market.secondhandmarketplace.application.dto.category.CategoryDto;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

public class CategoryRequest {


    @Builder
    @Getter @Setter
    public static class RegisterCategory {
        @NotBlank(message = "신규 등록 할 카테고리의 이름은 필수로 입력해야합니다.")
        private String name;
        private Long parentId;

        public CategoryDto.RegisterCategory toDto() {
            return CategoryDto.RegisterCategory.builder()
                    .name(name)
                    .parentId(parentId)
                    .build();
        }
    }
}
