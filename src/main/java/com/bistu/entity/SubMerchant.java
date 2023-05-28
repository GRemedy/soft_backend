package com.bistu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubMerchant {
    private int id;
    private int userId;
    private String storeName;
    private String license;
    private Integer storeRank;
}
