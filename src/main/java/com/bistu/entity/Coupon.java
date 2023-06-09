package com.bistu.entity;

import com.bistu.Enum.CouponType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {
    private Integer id;
    private Integer userId;
    private CouponType couponType;
    private LocalDate expiryDate;
}
