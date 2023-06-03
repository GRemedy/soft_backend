package com.bistu.mapper;

import com.bistu.dis.DisProduct;
import com.bistu.entity.Product;
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
    List<DisProduct> getAll(Product product,Integer grade, String storeName , boolean isDESC);

}
