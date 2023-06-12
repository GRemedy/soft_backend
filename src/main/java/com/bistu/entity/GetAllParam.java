package com.bistu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Gremedy
 * @Description:
 * @Date : 2023/6/6
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllParam {
   private Integer start;
   private Integer pageSize;
   private String name;
   private String category;
   private String storeName ;
   private String sortKey;
   private Boolean isDESC;
}
