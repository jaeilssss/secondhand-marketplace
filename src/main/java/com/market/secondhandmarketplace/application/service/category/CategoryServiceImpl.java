package com.market.secondhandmarketplace.application.service.category;

import com.market.secondhandmarketplace.application.dto.category.CategoryDto;
import com.market.secondhandmarketplace.domain.entity.category.Category;
import com.market.secondhandmarketplace.domain.repository.category.CategoryRepository;
import com.market.secondhandmarketplace.globals.error.CategoryErrorCode;
import com.market.secondhandmarketplace.globals.exception.BaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public boolean registerCategory(CategoryDto.RegisterCategory registerCategory) {
        Category category = null;
        if(registerCategory.getParentId() != -1L) {
            category = registerCategory.toEntity(getCategory(registerCategory.getParentId()));
        } else {
            category = registerCategory.toEntity();
        }
        categoryRepository.save(category);
        return true;
    }

    @Override
    public List<CategoryDto.CategoryResponse> getAllParentCategoryList() {
        return categoryRepository.findByParentCategoryList().stream().map(Category::toResponse).collect(Collectors.toList());
    }

    public Category getCategory(Long categoryId) {
        return categoryRepository.findCategory(categoryId)
                .orElseThrow(() -> new BaseException(
                        CategoryErrorCode.NOT_EXIST_CATEGORY.getMessage(),
                        HttpStatus.NOT_FOUND
                ));
    }
}
