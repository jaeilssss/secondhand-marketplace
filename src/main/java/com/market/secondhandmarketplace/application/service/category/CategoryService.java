package com.market.secondhandmarketplace.application.service.category;

import com.market.secondhandmarketplace.application.dto.category.CategoryDto;

import java.util.List;

public interface CategoryService {
    public boolean registerCategory(CategoryDto.RegisterCategory registerCategory);
    public List<CategoryDto.CategoryResponse> getAllParentCategoryList();
}
