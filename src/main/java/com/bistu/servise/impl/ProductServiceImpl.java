package com.bistu.servise.impl;

import com.bistu.Enum.TransactionStatus;
import com.bistu.dis.DisProduct;
import com.bistu.entity.*;
import com.bistu.mapper.ProductMapper;
import com.bistu.mapper.UserMapper;
import com.bistu.servise.ProductService;
import com.bistu.utils.ProToDisProMap;
import com.bistu.utils.UpdateRating;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final UpdateRating updateRating;


    public ProductServiceImpl(ProductMapper productMapper, ProToDisProMap proToDisProMap, UserMapper userMapper, UpdateRating updateRating) {
        this.productMapper = productMapper;
        this.proToDisProMap = proToDisProMap;
        this.userMapper = userMapper;
        this.updateRating = updateRating;
    }


    @Override
    public PageBean getAll(Map<String, Object> paraMap) {
        Long count = productMapper.getCount(paraMap);
        List<Product> products = productMapper.getAll(paraMap);
        List<DisProduct> disProducts = proToDisProMap.proToDisProMap(products);

        return new PageBean(count, disProducts);
    }

    @Override
    @Transactional
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
                        transaction.setCreateTime(LocalDateTime.now());
                        transaction.setPaymentTime(LocalDateTime.now());
                        transaction.setUpdateTime(LocalDateTime.now());
                        transaction.setDiscount(coupon.getCouponType());
                        transaction.setStatus(TransactionStatus.WAITING_FOR_SHIPPING);
                        productMapper.perchase(transaction);
                        userMapper.perchase(paid,LocalDateTime.now(),transaction.getUserId());
                        productMapper.updateProduct(transaction);
                }
                }
                else {
                    double paid = product.getPrice()*transaction.getQuantity();
                    transaction.setPaid(paid);
                    transaction.setCreateTime(LocalDateTime.now());
                    transaction.setPaymentTime(LocalDateTime.now());
                    transaction.setUpdateTime(LocalDateTime.now());
                    transaction.setDiscount(null);
                    transaction.setStatus(TransactionStatus.WAITING_FOR_SHIPPING);
                    productMapper.perchase(transaction);
                    userMapper.perchase(paid,LocalDateTime.now(),transaction.getUserId());
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
    public void comment(Comment comment) {
        comment.setCreateTime(LocalDateTime.now());
        comment.setUpdateTime(LocalDateTime.now());
        productMapper.comment(comment);
        updateRating.updateRate(comment.getProductId());
    }

    @Override
    public List<Coupon> prePerchase(Integer id) {
        return productMapper.getCoupon(id);
    }
}
