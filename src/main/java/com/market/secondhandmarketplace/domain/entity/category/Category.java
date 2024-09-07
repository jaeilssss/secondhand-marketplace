package com.market.secondhandmarketplace.domain.entity.category;

import com.market.secondhandmarketplace.application.dto.category.CategoryDto;
import com.market.secondhandmarketplace.domain.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class Category extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    private Category parent;

    private int depth;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "parent")
    private List<Category> children =  new ArrayList<>();

    public CategoryDto.CategoryResponse toResponse() {
        return CategoryDto.CategoryResponse.builder()
                .name(name)
                .id(id)
                .children(children.stream().map(Category::toResponse).toList())
                .build();
    }

}
