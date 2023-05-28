package com.bistu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private Integer id;
    private Integer userId;
    private Double balance;
    private Integer point;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
