package com.market.secondhandmarketplace.Infrastructure.repository.image;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.market.secondhandmarketplace.application.api.payment.ImageUploader;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class S3ImageUploaderImpl implements ImageUploader {
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Override
    public List<String> upload(List<MultipartFile> files, Long memberId) {
        ArrayList<String> imageUrlList = new ArrayList<>();
        files.forEach(file -> {
            try {
                ObjectMetadata objectMetadata = new ObjectMetadata();
                objectMetadata.setContentLength(file.getSize());
                objectMetadata.setContentType(file.getContentType());
                LocalDateTime currentDateTime = LocalDateTime.now();
                String fileName = currentDateTime.toString() + "/"+ String.valueOf(memberId) + file.getName();
                amazonS3Client.putObject(bucket, fileName,file.getInputStream(), objectMetadata);
                imageUrlList.add(amazonS3Client.getUrl(bucket, fileName).toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return imageUrlList;
    }
}
