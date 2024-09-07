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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/secondhand")
public class SecondHandController {
    private final SecondHandService secondHandService;
    private final MemberService memberService;
    private final CategoryService categoryService;

    @PostMapping("/post")
    public Response postSecondHand(
            @RequestBody @Valid SecondHandRequest.PostSecondHand postSecondHand
    ) {
        return Response.success(
                secondHandService.postSecondHand(
                        postSecondHand.toDto(),
                        memberService.getMember(postSecondHand.getMemberId()),
                        categoryService.getCategory(postSecondHand.getCategoryId()))
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

    @PostMapping("/image")
    public Response imageUpload(@RequestPart MultipartFile image) throws IOException {
        return Response.success(
                true,
                APIResponseCode.OK.getMessage()
        );
    }
}
