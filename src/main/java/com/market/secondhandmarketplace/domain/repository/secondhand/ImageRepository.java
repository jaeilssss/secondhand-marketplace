package com.market.secondhandmarketplace.domain.repository.secondhand;

import com.market.secondhandmarketplace.domain.entity.image.Images;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Images, Long> {

}
