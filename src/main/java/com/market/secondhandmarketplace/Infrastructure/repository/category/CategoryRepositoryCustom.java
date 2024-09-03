package com.market.secondhandmarketplace.Infrastructure.repository.category;

import com.market.secondhandmarketplace.domain.entity.category.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepositoryCustom {

    public Optional<Category> findCategory(Long categoryId);
    public List<Category> findByParentCategoryList();
}
