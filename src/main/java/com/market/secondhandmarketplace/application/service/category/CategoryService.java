package com.market.secondhandmarketplace.application.service.category;

import com.market.secondhandmarketplace.application.dto.category.CategoryDto;
import com.market.secondhandmarketplace.domain.entity.category.Category;

import java.util.List;

public interface CategoryService {
    public boolean registerCategory(CategoryDto.RegisterCategory registerCategory);
    public List<CategoryDto.CategoryResponse> getAllParentCategoryList();
    public Category getCategory(Long categoryId);
}
