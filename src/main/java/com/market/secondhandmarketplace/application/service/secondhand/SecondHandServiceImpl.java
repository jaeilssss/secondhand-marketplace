package com.market.secondhandmarketplace.application.service.secondhand;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.market.secondhandmarketplace.Infrastructure.caller.weather.WeatherApiCaller;
import com.market.secondhandmarketplace.Infrastructure.caller.weather.WeatherApiCallerImpl;
import com.market.secondhandmarketplace.application.api.payment.ImageUploader;
import com.market.secondhandmarketplace.application.dto.secondhand.SecondHandDto;
import com.market.secondhandmarketplace.domain.entity.category.Category;
import com.market.secondhandmarketplace.domain.entity.image.Images;
import com.market.secondhandmarketplace.domain.entity.member.Member;
import com.market.secondhandmarketplace.domain.entity.secondhand.SecondHand;
import com.market.secondhandmarketplace.domain.repository.secondhand.ImageRepository;
import com.market.secondhandmarketplace.domain.repository.secondhand.SecondHandRepository;
import com.market.secondhandmarketplace.globals.error.SecondHandErrorCode;
import com.market.secondhandmarketplace.globals.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SecondHandServiceImpl implements SecondHandService {
    private final SecondHandRepository secondHandRepository;
    private final ImageRepository imageRepository;
    private final WeatherApiCaller weatherApiCaller;

    private final ImageUploader imageUploader;

    @Override
    @Transactional
    public Boolean postSecondHand(
            SecondHandDto.PostSecondHand postSecondHand,
            Member member,
            Category category,
            List<MultipartFile> multipartFiles
    ) {
        List<String> imageUrlList = imageUploader.upload(multipartFiles, member.getId());
        postSecondHand.setImageUrlList(imageUrlList);
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

    @Override
    @Cacheable(cacheNames = "weather", key = "#key")
    public String getWeatherInfo(Double lat, Double lon, String key) {
        return weatherApiCaller.getWeatherData(lat, lon).getWeather().get(0).getMain();
    }

    @Async
    public CompletableFuture<List<String>> imageUpload(List<MultipartFile> multipartFiles, Long memberId) throws ExecutionException, InterruptedException {
        return CompletableFuture.completedFuture(imageUploader.upload(multipartFiles, memberId));
    }



    public SecondHand saveSecondHand(SecondHand secondHand) {
        return secondHandRepository.save(secondHand);
    }
}
