package com.bistu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubMerchant {
    private Integer id;
    private Integer userId;
    private String storeName;
    private String license;
    private Integer storeRank;
}
