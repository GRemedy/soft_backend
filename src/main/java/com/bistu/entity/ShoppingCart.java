package com.bistu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCart {
    private Integer id;
    private Integer userId;
    private Integer productId;
    private Integer quantity;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
