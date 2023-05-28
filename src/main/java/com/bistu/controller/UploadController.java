package com.bistu.controller;

import com.bistu.entity.Result;
import com.bistu.utils.ALOSSUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class UploadController {

    private final ALOSSUtils alossUtils;

    public UploadController(ALOSSUtils alossUtils) {
        this.alossUtils = alossUtils;
    }

    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        String url = alossUtils.upload(image);
        return Result.success(url);
    }

}
