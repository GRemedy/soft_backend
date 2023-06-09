package com.bistu.entity;

import com.bistu.Enum.CouponType;
import com.bistu.Enum.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Transaction {
    private int id;
    private int userId;
    private int productId;
    private int quantity;
    private CouponType discount;
    private TransactionStatus status;
    private Double paid;
    private LocalDateTime createTime;
    private LocalDateTime paymentTime;
    private LocalDateTime deliveryTime;
    private LocalDateTime dealTime;
    private LocalDateTime updateTime;
    public Transaction(){
        this.quantity = 1;
    }
}
