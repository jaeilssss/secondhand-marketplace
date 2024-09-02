package com.market.secondhandmarketplace.domain.entity.member;

import com.market.secondhandmarketplace.domain.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
public class DeleteMember extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delete_member_id")
    private Long id;
    // 삭제되기 전 Id
    private Long beforeId;
    private String name;
    private String email;

}
