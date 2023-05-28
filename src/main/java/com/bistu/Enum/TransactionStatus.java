package com.bistu.Enum;

public enum TransactionStatus {
    WAITING_FOR_PAYMENT, // 待支付
    WAITING_FOR_SHIPPING, // 待发货
    SHIPPED, // 已发货
    RECEIVED, //已收货
    REFUNDING, //退款中
    REFUNDED, //退款成功
    REFUND_FAILED, //退款失败
    TRADE_CLOSED  //交易关闭
}
