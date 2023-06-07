package com.bistu.entity;

/**
 * @Author: Gremedy
 * @Description:
 * @Date : 2023/6/7
 */
public interface BaseError {
    /**
     *  错误码
     * @return String
     */
    String getResultCode();

    /**
     * 错误描述
     * @return String
     */
    String getResultMsg();
}
