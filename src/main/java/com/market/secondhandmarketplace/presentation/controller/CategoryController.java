package com.market.secondhandmarketplace.presentation.controller;

import com.market.secondhandmarketplace.application.service.category.CategoryService;
import com.market.secondhandmarketplace.presentation.Response;
import com.market.secondhandmarketplace.presentation.enums.APIResponseCode;
import com.market.secondhandmarketplace.presentation.request.category.CategoryRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;
    @PostMapping
    public Response registerCategory(@RequestBody @Valid CategoryRequest.RegisterCategory registerCategory) {
        return Response.success(
                categoryService.registerCategory(registerCategory.toDto()),
                APIResponseCode.OK.getMessage()
        );
    }

    @GetMapping
    public Response getAllCategory() {
        return Response.success(
                categoryService.getAllParentCategoryList(),
                APIResponseCode.OK.getMessage()
        );
    }
}
