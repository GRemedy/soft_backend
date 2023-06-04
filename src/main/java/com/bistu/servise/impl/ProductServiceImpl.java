package com.bistu.servise.impl;

import com.bistu.Enum.TransactionStatus;
import com.bistu.dis.DisProduct;
import com.bistu.entity.*;
import com.bistu.mapper.ProductMapper;
import com.bistu.mapper.UserMapper;
import com.bistu.servise.ProductService;
import com.bistu.utils.ProToDisProMap;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @Author: Gremedy
 * @Description:
 * @Date : 2023/5/26
 */
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    private final ProToDisProMap proToDisProMap;
    private final UserMapper userMapper;


    public ProductServiceImpl(ProductMapper productMapper, ProToDisProMap proToDisProMap, UserMapper userMapper) {
        this.productMapper = productMapper;
        this.proToDisProMap = proToDisProMap;
        this.userMapper = userMapper;
    }


    @Override
    public PageBean getAll(Map<String, Object> paraMap) {
        Long count = productMapper.getCount(paraMap);
        List<Product> products = productMapper.getAll(paraMap);
        List<DisProduct> disProducts = proToDisProMap.proToDisProMap(products);

        return new PageBean(count, disProducts);
    }

    @Override
    public void perchase(Transaction transaction ,Integer couponId) {
        Product product = productMapper.getProduct(transaction.getProductId());
        if (transaction.getQuantity() <= product.getQuantity()){
            Account account = userMapper.getAccount(transaction.getUserId());
            if (account.getBalance() >=product.getPrice()){
                if (couponId != null){
                    Coupon coupon = productMapper.useCoupon(couponId);
                    double value = coupon.getCouponType().getValue();
                    if (value > 1){
                        double paid = product.getPrice() * transaction.getQuantity() - value;
                        transaction.setPaid(paid);
                        transaction.setCreateTime(LocalDate.now());
                        transaction.setPaymentTime(LocalDate.now());
                        transaction.setUpdateTime(LocalDate.now());
                        transaction.setDiscount(coupon.getCouponType());
                        transaction.setStatus(TransactionStatus.WAITING_FOR_SHIPPING);
                        productMapper.perchase(transaction);
                        userMapper.perchase(paid,transaction.getUserId());
                        productMapper.updateProduct(transaction);
                }
                }
                else {
                    double paid = product.getPrice()*transaction.getQuantity();
                    transaction.setPaid(paid);
                    transaction.setCreateTime(LocalDate.now());
                    transaction.setPaymentTime(LocalDate.now());
                    transaction.setUpdateTime(LocalDate.now());
                    transaction.setDiscount(null);
                    transaction.setStatus(TransactionStatus.WAITING_FOR_SHIPPING);
                    productMapper.perchase(transaction);
                    userMapper.perchase(paid,transaction.getUserId());
                    productMapper.updateProduct(transaction);
                }
            }
        }
    }

    @Override
    public void shoppingCart(ShoppingCart shoppingCart) {
        shoppingCart.setCreateTime(LocalDateTime.now());
        shoppingCart.setUpdateTime(LocalDateTime.now());
        productMapper.shoppingCart(shoppingCart);
    }

    @Override
    public List<Coupon> prePerchase(Integer id) {
        return productMapper.getCoupon(id);
    }
}
