package com.bistu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author: Gremedy
 * @Description:
 * @Date : 2023/6/5
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChargeRecord {
    private Integer id;
    private Integer userId;
    private Integer amount;
    private LocalDateTime chargeTime;
}
