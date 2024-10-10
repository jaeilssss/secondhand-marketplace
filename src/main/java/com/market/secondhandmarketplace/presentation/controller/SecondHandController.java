package com.market.secondhandmarketplace.presentation.controller;

import com.market.secondhandmarketplace.application.service.category.CategoryService;
import com.market.secondhandmarketplace.application.service.member.MemberService;
import com.market.secondhandmarketplace.application.service.secondhand.SecondHandService;
import com.market.secondhandmarketplace.presentation.Response;
import com.market.secondhandmarketplace.presentation.enums.APIResponseCode;
import com.market.secondhandmarketplace.presentation.request.secondhand.SecondHandRequest;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.List;
import java.util.concurrent.ExecutionException;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/secondhand")
public class SecondHandController {
    private final SecondHandService secondHandService;
    private final MemberService memberService;
    private final CategoryService categoryService;

    @PostMapping("/post")
    public Response postSecondHand(
            @RequestPart("postSecondHand") @Valid SecondHandRequest.PostSecondHand postSecondHand,
            @RequestPart("multipartFiles") List<MultipartFile> multipartFiles
    ) throws IOException, ExecutionException, InterruptedException {

        return Response.success(
                secondHandService.postSecondHand(
                        postSecondHand.toDto(),
                        memberService.getMember(postSecondHand.getMemberId()),
                        categoryService.getCategory(postSecondHand.getCategoryId()),
                        multipartFiles)

        );
    }

    @GetMapping
    public Response getMyAreaSecondHandList(
            @RequestParam("lat") Double latitude,
            @RequestParam("lon") Double longitude,
            @RequestParam("page") int page
    ) {
        return Response.success(
                secondHandService.getMyAreaSecondHand(latitude, longitude, page),
                APIResponseCode.OK.getMessage()
        );
    }

    @PutMapping
    public Response modifySecondHand(
            @RequestParam("secondhandId") Long id,
            @RequestBody SecondHandRequest.ModifySecondHand modifySecondHand
    ) {
        return Response.success(
            secondHandService.modifySecondHand(id, modifySecondHand.toDto()),
            APIResponseCode.OK.getMessage()
        );
    }

    @DeleteMapping
    public Response deleteSecondHand(@RequestParam("secondhandId") Long id) {
        return Response.success(
                secondHandService.deleteSecondHand(id),
                APIResponseCode.OK.getMessage()
        );
    }

    @GetMapping("/mysecondHand")
    public Response getMySecondHand(@RequestParam("page") int page, @RequestParam("memberId") Long memberId) {
        return Response.success(
                secondHandService.mySecondHandList(page, memberId),
                APIResponseCode.OK.getMessage()
        );
    }


    @GetMapping("/weather")
    public Response getWeatherInfo(@RequestParam("lat") Double lat, @RequestParam("lon") Double lon) {
        return Response.success(
                secondHandService.getWeatherInfo(lat, lon,lat.toString() + lon.toString()),
                APIResponseCode.OK.getMessage()
        );
    }
}
