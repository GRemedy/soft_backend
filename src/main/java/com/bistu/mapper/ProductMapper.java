package com.bistu.mapper;

import com.bistu.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Author: Gremedy
 * @Description:
 * @Date : 2023/5/26
 */
@Mapper
public interface ProductMapper {

    List<Product> getAll(Map<String,Object> paraMap);

    @Select("SELECT COUNT(*) FROM product")
    Long getCount(Map<String, Object> paramMap);

}
