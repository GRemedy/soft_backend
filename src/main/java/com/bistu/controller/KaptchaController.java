package com.bistu.controller;

import com.bistu.entity.Result;
import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

/**
 * @Author: Gremedy
 * @Description:
 * @Date : 2023/6/3
 */
@RestController
@RequestMapping("/code")
@Slf4j
public class KaptchaController {

    private final Producer kaptchaProducer;

    public KaptchaController(Producer kaptchaProducer) {
        this.kaptchaProducer = kaptchaProducer;
    }


    @GetMapping
    public Result generateCaptcha() throws IOException {
        // 生成验证码图像
        String text=kaptchaProducer.createText();
        log.info(text);
        // 生成图片验证码
        BufferedImage image = kaptchaProducer.createImage(text);
        ByteArrayOutputStream out=new ByteArrayOutputStream();
        ImageIO.write(image,"jpg",out);
        // 对字节组Base64编码
        return Result.success( Base64.getEncoder().encodeToString(out.toByteArray()));

    }
}
