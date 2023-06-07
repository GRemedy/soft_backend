package com.bistu.exception;

import com.bistu.entity.BaseError;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Gremedy
 * @Description:
 * @Date : 2023/6/7
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DefaultException extends RuntimeException{

    private String errorCode;
    /**
     * 错误信息
     */
    private String errorMsg;

    public DefaultException() {
        super();
    }

    public DefaultException(BaseError errorInfoInterface) {
        super(errorInfoInterface.getResultCode());
        this.errorCode = errorInfoInterface.getResultCode();
        this.errorMsg = errorInfoInterface.getResultMsg();
    }

    public DefaultException(BaseError errorInfoInterface, Throwable cause) {
        super(errorInfoInterface.getResultCode(), cause);
        this.errorCode = errorInfoInterface.getResultCode();
        this.errorMsg = errorInfoInterface.getResultMsg();
    }

    public DefaultException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public DefaultException(String errorCode, String errorMsg) {
        super(errorCode);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public DefaultException(String errorCode, String errorMsg, Throwable cause) {
        super(errorCode, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }


}
