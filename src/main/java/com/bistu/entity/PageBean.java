package com.bistu.entity;

import com.bistu.dis.DisProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Gremedy
 * @Description:
 * @Date : 2023/5/26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class PageBean {
    private Long total;
    private List<DisProduct> result;
}
