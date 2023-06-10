package com.bistu.entity;

import com.bistu.Enum.CouponType;
import com.bistu.Enum.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private Integer id;
    private Integer userId;
    private Integer productId;
    private Integer quantity;
    private CouponType discount;
    private TransactionStatus status;
    private Double paid;
    private LocalDateTime createTime;
    private LocalDateTime paymentTime;
    private LocalDateTime deliveryTime;
    private LocalDateTime dealTime;
    private LocalDateTime updateTime;

}
