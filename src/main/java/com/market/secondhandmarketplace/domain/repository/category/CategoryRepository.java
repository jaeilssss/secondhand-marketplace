package com.market.secondhandmarketplace.domain.repository.category;

import com.market.secondhandmarketplace.Infrastructure.repository.category.CategoryRepositoryCustom;
import com.market.secondhandmarketplace.domain.entity.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryRepositoryCustom {
}
