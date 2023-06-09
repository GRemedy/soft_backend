package com.bistu.servise.impl;

import com.bistu.Enum.ProductStatus;
import com.bistu.Enum.PurchaseMethod;
import com.bistu.Enum.TransactionStatus;
import com.bistu.dis.DisProduct;
import com.bistu.entity.*;
import com.bistu.mapper.ProductMapper;
import com.bistu.mapper.UserMapper;
import com.bistu.servise.ProductService;
import com.bistu.utils.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private final TransactionUtils transactionUtils;
    private final CouponUtils couponUtils;
    private final PointUtils pointUtils;

    private final ShoppingCartUtils shoppingCartUtils;


    public ProductServiceImpl(ProductMapper productMapper, ProToDisProMap proToDisProMap, UserMapper userMapper, UpdateRating updateRating, TransactionUtils transactionUtils, CouponUtils couponUtils, PointUtils pointUtils, ShoppingCartUtils shoppingCartUtils) {
        this.productMapper = productMapper;
        this.proToDisProMap = proToDisProMap;
        this.userMapper = userMapper;
        this.updateRating = updateRating;
        this.transactionUtils = transactionUtils;
        this.couponUtils = couponUtils;
        this.pointUtils = pointUtils;
        this.shoppingCartUtils = shoppingCartUtils;
    }


    @Override
    public PageBean getAll(GetAllParam getAllParam) {
        Long count = productMapper.getCount(getAllParam);
        getAllParam.setStart((getAllParam.getStart()-1) * getAllParam.getPageSize());
        List<Product> products = productMapper.getAll(getAllParam);
        List<DisProduct> disProducts = proToDisProMap.proToDisProMap(products);
        disProducts.forEach(
                disProduct -> {
                    List<Comment> comments = productMapper.getProductComment(disProduct.getId());
                    disProduct.setComments(comments);
                }
        );
        return new PageBean(count, disProducts);
    }

    @Override
    @Transactional
    public void perchase(Transaction transaction ,Integer couponId) {
        Product product = productMapper.getProduct(transaction.getProductId());
        if (transaction.getQuantity() <= product.getQuantity() ){
            Account account = userMapper.getAccount(transaction.getUserId());
            Integer point = userMapper.getPoint(transaction.getUserId());
            if (account.getBalance() >=product.getPrice()){
                if (couponId != null){
                    Coupon coupon = productMapper.useCoupon(couponId);
                    Map<String, Double> pointed = pointUtils.point(point,
                            couponUtils.getCoupon(coupon, product.getPrice() * transaction.getQuantity()));
                    userMapper.minusPoint(pointed.get("point"),transaction.getUserId());
                    transactionUtils.tra(pointed.get("actualPaid"),transaction,coupon);
                }

                else {
                    Map<String, Double> pointed = pointUtils.point(point,product.getPrice() * transaction.getQuantity());
                    userMapper.minusPoint(pointed.get("point"),transaction.getUserId());
                    transactionUtils.tra(pointed.get("actualPaid"),transaction, null);
                }
            }
        }
    }

    @Override
    public void shoppingCart(Integer id,List<Integer> ids ,List<Integer> quantity) {
        List<ShoppingCart> shoppingCarts = shoppingCartUtils.shoppingCartUtils(id, ids,quantity);
        productMapper.shoppingCart(shoppingCarts);

    }

    @Override
    public void comment(Comment comment) {
        comment.setCreateTime(LocalDateTime.now());
        comment.setUpdateTime(LocalDateTime.now());
        productMapper.comment(comment);
        updateRating.updateRate(comment.getProductId());
    }

    /**
     * @param product 商品对象
     */
    @Override
    public void shelves(Product product) {
        product.setCreateTime(LocalDateTime.now());
        product.setUpdateTime(LocalDateTime.now());
        product.setStatus(ProductStatus.REVIEWING);
        product.setQuantity(1);
        product.setPurchaseMethod(PurchaseMethod.Online);
        if (product.getNegotiable()==null){
            product.setNegotiable(true);
        }
        productMapper.shelves(product);

    }

    /**
     * @param ids 下架商品的Id
     */
    @Override
    public void offShelves(List<Integer> ids) {
        String idString = ids.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
        String idsString = "(" + idString + ")";
        productMapper.offShelves(idsString,LocalDateTime.now());
    }

    @Override
    public void delivery(Transaction transaction) {
        transaction.setDeliveryTime(LocalDateTime.now());
        transaction.setStatus(TransactionStatus.SHIPPED);
        transaction.setUpdateTime(LocalDateTime.now());
        productMapper.delivery(transaction);
    }

    @Override
    public DisProduct getMessage(Integer id) {
        Product product = productMapper.getProduct(id);
        return proToDisProMap.proToDisProMap(product);
    }


    @Override
    public Map<String,List<Object>> prePerchase(Transaction transaction) {
        List<Coupon> coupons = productMapper.getCoupon(transaction.getUserId());
        List<Comment> comments = productMapper.getProductComment(transaction.getProductId());
        Product product = productMapper.getProduct(transaction.getProductId());
        Map<String,List<Object>> prePerchase = new HashMap<>();
        prePerchase.put("coupons", Collections.singletonList(coupons));
        prePerchase.put("comments", Collections.singletonList(comments));
        prePerchase.put("perchaseMethod", Collections.singletonList(product.getPurchaseMethod()));
        return prePerchase;
    }
}
