package com.bistu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private int id;
    private int userId;
    private int productId;
    private int grade;
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
