package com.bistu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private Integer id;
    private Integer userId;
    private Integer productId;
    private Integer grade;
    private String content;
    private Integer attitude;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
