package com.market.secondhandmarketplace.application.api.payment;


import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ImageUploader {
    public List<String> upload(List<MultipartFile> files, Long memberId);
}
