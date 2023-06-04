package com.bistu.entity;

import com.bistu.Enum.CouponType;
import com.bistu.Enum.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private int id;
    private int userId;
    private int productId;
    private int quantity;
    private CouponType discount;
    private TransactionStatus status;
    private double paid;
    private LocalDate createTime;
    private LocalDate paymentTime;
    private LocalDate deliveryTime;
    private LocalDate dealTime;
    private LocalDate updateTime;
}
