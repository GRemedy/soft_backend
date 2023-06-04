package com.bistu.entity;

import com.bistu.Enum.DamageLevel;
import com.bistu.Enum.ProductStatus;
import com.bistu.Enum.PurchaseMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    private double price;
    private String image;
    private boolean negotiable;
    private PurchaseMethod purchaseMethod;
    private int quantity;
    private int salesVolume;
    private DamageLevel damageLevel;
    private ProductStatus status;
    private int perchaseCount;
    private double rating;
    private LocalDate createTime;
    private LocalDate updateTime;
}
