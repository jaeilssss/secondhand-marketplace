package com.market.secondhandmarketplace.application.service.secondhand;

import com.market.secondhandmarketplace.application.dto.secondhand.SecondHandDto;
import com.market.secondhandmarketplace.domain.entity.category.Category;
import com.market.secondhandmarketplace.domain.entity.member.Member;
import com.market.secondhandmarketplace.domain.entity.secondhand.SecondHand;
import com.market.secondhandmarketplace.domain.repository.secondhand.SecondHandRepository;
import com.market.secondhandmarketplace.globals.error.SecondHandErrorCode;
import com.market.secondhandmarketplace.globals.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SecondHandServiceImpl implements SecondHandService {
    private final SecondHandRepository secondHandRepository;

    @Override
    @Transactional
    public boolean postSecondHand(
            SecondHandDto.PostSecondHand postSecondHand,
            Member member,
            Category category
    ) {
        secondHandRepository.save(postSecondHand.toEntity(category, member));
        return true;
    }

    @Override
    public List<SecondHandDto.SecondHandResponse> getMyAreaSecondHand(Double latitude, Double longitude, int page) {
        return secondHandRepository.findByLocation(page, latitude, longitude)
                .stream()
                .map(SecondHand::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public SecondHandDto.SecondHandResponse modifySecondHand(Long id, SecondHandDto.ModifySecondHand modifySecondHand) {
        SecondHand secondHand = getSecondHand(id);
        secondHand.modify(modifySecondHand);
        return secondHand.toResponse();
    }

    @Override
    @Cacheable(cacheNames = "MyListSecondHand", key = "#memberId")
    public List<SecondHandDto.SecondHandResponse> mySecondHandList(int page, Long id) {
        return secondHandRepository.findMySecondHandList(page, id)
                .stream()
                .map(SecondHand::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public boolean deleteSecondHand(Long id) {
        secondHandRepository.delete(getSecondHand(id));
        return true;
    }

    public SecondHand getSecondHand(Long id) {
        return secondHandRepository.findById(id)
                .orElseThrow(() -> new BaseException(
                        SecondHandErrorCode.NOT_FOUND_SECONDHAND.getMessage(),
                        HttpStatus.NOT_FOUND
                ));
    }


}
