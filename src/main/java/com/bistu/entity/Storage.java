package com.bistu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @Author: Gremedy
 * @Description:
 * @Date : 2023/6/10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Storage {

    private String captcha;
}
