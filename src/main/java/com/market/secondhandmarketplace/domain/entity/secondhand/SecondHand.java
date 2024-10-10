package com.market.secondhandmarketplace.domain.entity.secondhand;

import com.market.secondhandmarketplace.application.dto.secondhand.SecondHandDto;
import com.market.secondhandmarketplace.domain.entity.AbstractEntity;
import com.market.secondhandmarketplace.domain.entity.category.Category;
import com.market.secondhandmarketplace.domain.entity.image.Images;
import com.market.secondhandmarketplace.domain.entity.member.Member;
import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Point;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class SecondHand extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "secondhand_id")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String description;

    private Point location;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "secondhand_id")
    private List<Images> imagesUrlList;
    public SecondHandDto.SecondHandResponse toResponse() {
        return SecondHandDto.SecondHandResponse.builder()
                .name(name)
                .id(id)
                .categoryName(category.getName())
                .price(price)
                .postMemberName(member.getName())
                .description(description)
                .build();
    }

    public void modify(SecondHandDto.ModifySecondHand modifySecondHand) {
        name = modifySecondHand.getName();
        price = modifySecondHand.getPrice();
        description = modifySecondHand.getDescription();
    }
}
