package com.market.secondhandmarketplace.Infrastructure.repository.category;

import com.market.secondhandmarketplace.domain.entity.category.Category;

import com.market.secondhandmarketplace.domain.entity.category.QCategory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepositoryCustomImpl implements CategoryRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
    QCategory qCategory = QCategory.category;
    public CategoryRepositoryCustomImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }


    @Override
    public Optional<Category> findCategory(Long categoryId) {
        QCategory childCategory = new QCategory("childCategory");
        return Optional.ofNullable(jpaQueryFactory.selectFrom(qCategory)
                .leftJoin(qCategory.children, childCategory).fetchJoin()
                .where(qCategory.id.eq(categoryId))
                .fetchOne());
    }

    @Override
    public List<Category> findByParentCategoryList() {
        QCategory childCategory = new QCategory("children");

        return jpaQueryFactory.selectFrom(qCategory)
                .leftJoin(qCategory.children, childCategory).fetchJoin()  // fetchJoin 사용
                .where(qCategory.depth.eq(0))
                .distinct()
                .fetch();
    }
}
