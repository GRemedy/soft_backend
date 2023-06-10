package com.bistu.servise.impl;

import com.bistu.Enum.Identity;
import com.bistu.Enum.TransactionStatus;
import com.bistu.dis.DisProduct;
import com.bistu.dis.DisUser;
import com.bistu.entity.*;
import com.bistu.exception.DefaultException;
import com.bistu.mapper.UserMapper;
import com.bistu.servise.UserService;
import com.bistu.utils.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.bistu.utils.EncodePassword.encodePassword;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserToDisUserMap userToDisUserMap;
    private final ProToDisProMap proToDisProMap;
    private final TransToProductMap transToProductMap;
    private final MerchantFeeUtils merchantFeeUtils;
    private final RefundUtils refundUtils;
    private final TimeUtils timeUtils;
    private final ShoppingCartToTransactionMap shoppingCartToTransactionMap;


    public UserServiceImpl(UserMapper userMapper, UserToDisUserMap userToDisUserMap, ProToDisProMap proToDisProMap, TransToProductMap transToProductMap, MerchantFeeUtils merchantFeeUtils, RefundUtils refundUtils, TimeUtils timeUtils, ShoppingCartToTransactionMap shoppingCartToTransactionMap) {
        this.userMapper = userMapper;
        this.userToDisUserMap = userToDisUserMap;
        this.proToDisProMap = proToDisProMap;
        this.transToProductMap = transToProductMap;
        this.merchantFeeUtils = merchantFeeUtils;
        this.refundUtils = refundUtils;
        this.timeUtils = timeUtils;
        this.shoppingCartToTransactionMap = shoppingCartToTransactionMap;
    }

    @Override
    public DisUser login(User user) {
        user.setPassword(encodePassword(user.getPassword()));
        return userToDisUserMap.userToDisUserMap(userMapper.login(user));
    }

    @Override
    @Transactional
    public void signup(User user) {
        user.setPassword(encodePassword(user.getPassword()));
        user.setUpdateTime(LocalDateTime.now());
        user.setCreateTime(LocalDateTime.now());
        if (user.getIdentity() == null) {
            user.setIdentity(Identity.User);
        }
        if (user.getState() == null) {
            user.setState(0);
        }
        userMapper.signup(user);
        userMapper.addAccount();
    }

    @Override
    public void audit(List<Integer> ids) {
        userMapper.audit(ids);
    }

    @Override
    public List<DisUser> selectStateEqual0() {
        return userToDisUserMap.userToDisUserMap(userMapper.selectStateEqual0());
    }

    @Override
    public void registerMerchant(Integer id, String storeName, String license) {
        userMapper.registerMerchant(id);
        SubMerchant subMerchant = new SubMerchant();
        subMerchant.setUserId(id);
        subMerchant.setLicense(license);
        subMerchant.setStoreName(storeName);
        userMapper.buildSubMerchant(subMerchant);
    }

    @Override
    public User queryMessage(Integer id) {
        return userMapper.queryMessage(id);
    }

    @Override
    public void updateMessage(User user) {
        user.setPassword(encodePassword(user.getPassword()));
        user.setUpdateTime(LocalDateTime.now());
        userMapper.updateMessage(user);
    }

    /**
     * @param id 用户id
     * @return List<DisProduct>
     * @author Gremedy
     * @date 2023/6/4 20:51
     **/

    @Override
    public List<DisProduct> historyData(Integer id) {
        List<Transaction> transactions = userMapper.historyData(id);
        List<Product> products = transToProductMap.transToProductMap(transactions);
        List<DisProduct> disProducts = proToDisProMap.proToDisProMap(products);

        Map<Integer, DisProduct> mergedMap = new HashMap<>();

        for (DisProduct disProduct : disProducts) {
            int productId = disProduct.getId();
            if (mergedMap.containsKey(productId)) {
                DisProduct mergedProduct = mergedMap.get(productId);
                // 合并当前DisProduct和已存在的DisProduct的属性
                mergedProduct.setSalesVolume(mergedProduct.getSalesVolume() + disProduct.getSalesVolume());
                // 在此处添加要合并的其他属性
                // ...
            } else {
                // 将当前DisProduct添加到Map中
                mergedMap.put(productId, disProduct);
            }
        }

        Map<Integer, List<LocalDateTime>> paymentTimeMap = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getProductId,
                        Collectors.mapping(Transaction::getPaymentTime, Collectors.toList())));
        for (DisProduct disProduct : mergedMap.values()) {
            int productId = disProduct.getId();
            if (paymentTimeMap.containsKey(productId)) {
                List<LocalDateTime> paymentTimes = paymentTimeMap.get(productId);
                disProduct.setPaymentTimes(paymentTimes);
            }
        }

        return new ArrayList<>(mergedMap.values());
    }

    @Override
    public Account getAccount(Integer id) {
        return userMapper.getAccount(id);
    }

    @Override
    public List<Comment> getComment(Integer id) {
        return userMapper.getComment(id);
    }

    @Override
    public List<ChargeRecord> getChargeRecord(Integer id) {
        return userMapper.getChargeRecord(id);
    }

    @Override
    public List<PaymentRecord> getPaymentRecord(Integer id) {
        return userMapper.getPaymentRecord(id);
    }


    @Override
    @Transactional
    public void charge(ChargeRecord chargeRecord) {
        chargeRecord.setChargeTime(LocalDateTime.now());
        userMapper.chargeAccount(chargeRecord);
        userMapper.charge(chargeRecord);
    }

    /**
     * @return 返回待审核的商品列表
     */
    @Override
    public List<DisProduct> getReviewProduct() {
        List<Product> products = userMapper.getReviewProduct();
        return proToDisProMap.proToDisProMap(products);
    }

    @Override
    public void review(List<Integer> ids) {
        userMapper.reviewing(ids);
    }


    @Override
    @Transactional
    public void pay(Transaction transaction){
        Integer rank = userMapper.getRank(transaction.getUserId());
        Double fee = merchantFeeUtils.getFee(transaction, rank);
        transaction.setPaid(transaction.getPaid()-fee);
        transaction.setUpdateTime(LocalDateTime.now());
        transaction.setStatus(TransactionStatus.RECEIVED);
        userMapper.updateTransaction(transaction);
        userMapper.pay(transaction);
    }

    @Override
    public void updateRank(Integer id, Integer rank) {
        userMapper.updateRank(id,rank);
    }

    @Override
    @Transactional
    public void refunding(SubTrade subTrade){
        Transaction transaction = userMapper.getUpdateTime(subTrade.getTransactionId());
        Boolean expressed = timeUtils.express24Hours(transaction.getUpdateTime(), LocalDateTime.now());
        if (expressed && transaction.getStatus() == TransactionStatus.RECEIVED){
            throw new DefaultException("0","超过24小时不能申请退款");
        }
       else {
            subTrade.setReturnTime(LocalDateTime.now());
            subTrade.setSuccess(0);
            userMapper.refunding(subTrade);
            userMapper.refund(subTrade.getTransactionId());
        }
    }

    @Override
    @Transactional
    public void dealRefund(SubTrade subTrade){
        Transaction transaction = userMapper.getTransaction(subTrade.getTransactionId());
        transaction.setDealTime(LocalDateTime.now());
        transaction.setUpdateTime(LocalDateTime.now());
        TransactionStatus status = refundUtils.getStatus(subTrade.getSuccess());
        transaction.setStatus(status);
        subTrade.setDealTime(LocalDateTime.now());
        if (subTrade.getSuccess() != 1){
            subTrade.setRejectReason("");
            userMapper.dealRefundAccount(transaction);
        }
        userMapper.dealRefunding(subTrade);
        userMapper.dealRefund(transaction);
    }

    @Override
    public List<SubTrade> getSubTrade(Integer id){
        return userMapper.getSubTrade(id);
    }

    @Override
    public List<ShoppingCart> getShoppingCart(Integer id){
        return userMapper.getShoppingCart(id);
    }

    @Override
    @Transactional
    public void quickPay(List<ShoppingCart> shoppingCarts){
        List<Transaction> transactions = shoppingCartToTransactionMap.shopMap(shoppingCarts);
        userMapper.quickPay(transactions);
        userMapper.dropShoppingCart(shoppingCarts);
    }
}
