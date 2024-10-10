package com.market.secondhandmarketplace.application.service.secondhand;

import com.market.secondhandmarketplace.application.dto.pay.PayDto;
import com.market.secondhandmarketplace.application.dto.secondhand.SecondHandDto;
import com.market.secondhandmarketplace.domain.entity.category.Category;
import com.market.secondhandmarketplace.domain.entity.member.Member;
import com.market.secondhandmarketplace.domain.entity.secondhand.SecondHand;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public interface SecondHandService {
    public Boolean postSecondHand(
            SecondHandDto.PostSecondHand postSecondHand,
            Member member,
            Category category,
            List<MultipartFile> multipartFiles
    ) throws IOException, ExecutionException, InterruptedException;

    public List<SecondHandDto.SecondHandResponse> getMyAreaSecondHand(Double latitude, Double longitude, int page);
    public SecondHandDto.SecondHandResponse modifySecondHand(Long id,SecondHandDto.ModifySecondHand modifySecondHand);
    public List<SecondHandDto.SecondHandResponse> mySecondHandList(int page, Long id);
    public boolean deleteSecondHand(Long id);
    public SecondHand getSecondHand(Long id);
//    public SecondHandDto.SecondHandResponse getSecondHand(Long id);

    public String getWeatherInfo(Double lat,  Double lon, String key);

}
