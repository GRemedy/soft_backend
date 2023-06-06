package com.bistu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: Gremedy
 * @Description:
 * @Date : 2023/6/6
 */
@Data
@AllArgsConstructor
public class GetAllParam {
   private int start;
   private int pageSize;
   private String name;
   private String category;
   private Double price;
   private int salesVolume;
   private String storeName ;
   private Double rating;
   boolean isDESC;
   public GetAllParam(){
      this.start = 1;
      this.pageSize = 20;
   }
}