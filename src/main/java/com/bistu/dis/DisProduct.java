package com.bistu.dis;

import com.bistu.Enum.DamageLevel;
import com.bistu.Enum.ProductStatus;
import com.bistu.Enum.PurchaseMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisProduct {
    private int id;
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
    private List<LocalDateTime> paymentTimes;

}
