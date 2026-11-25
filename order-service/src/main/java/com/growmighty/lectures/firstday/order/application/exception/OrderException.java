package com.growmighty.lectures.firstday.order.application.exception;

import lombok.Getter;

@Getter
public class OrderException extends RuntimeException {

    private final OrderErrorCode errorCode;

    public OrderException(OrderErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public OrderException(OrderErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public OrderException() {
        super(OrderErrorCode.UNKNOWN_ERROR.getMessage());
        this.errorCode = OrderErrorCode.UNKNOWN_ERROR;
    }
}
