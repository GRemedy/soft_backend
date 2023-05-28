package com.bistu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubTrade {
    private int id;
    private int transactionId;
    private LocalDate returnTime;
    private LocalDate successTime;
}
