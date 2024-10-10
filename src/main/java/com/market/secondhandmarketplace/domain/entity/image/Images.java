package com.market.secondhandmarketplace.domain.entity.image;

import com.market.secondhandmarketplace.domain.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class Images extends AbstractEntity {

    public Images(String url) {
        this.url = url;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    private String url;


}
