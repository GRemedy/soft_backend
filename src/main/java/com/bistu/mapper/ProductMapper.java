package com.bistu.mapper;

import com.bistu.dis.DisProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: Gremedy
 * @Description:
 * @Date : 2023/5/26
 */
@Mapper
public interface ProductMapper {
    @Select("select * from product")
    List<DisProduct> getAll(String name , String category , String storeName);

}
