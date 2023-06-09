package com.bistu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubTrade {
    private Integer id;
    private Integer transactionId;
    private String reason;
    private Integer success;
    private String rejectReason;
    private LocalDateTime returnTime;
    private LocalDateTime dealTime;
}
