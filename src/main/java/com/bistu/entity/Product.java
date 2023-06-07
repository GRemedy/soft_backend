package com.bistu.entity;

import com.bistu.Enum.DamageLevel;
import com.bistu.Enum.ProductStatus;
import com.bistu.Enum.PurchaseMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Integer id;
    private Integer storeId;
    private String name;
    private String category;
    private String size;
    private String description;
    private Double price;
    private String image;
    private Boolean negotiable;
    private PurchaseMethod purchaseMethod;
    private Integer quantity;
    private Integer salesVolume;
    private DamageLevel damageLevel;
    private ProductStatus status;
    private Double rating;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
