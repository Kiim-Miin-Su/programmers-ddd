package com.growmighty.lectures.firstday.order.application.exception;

public class NotOrderableException extends OrderException {

    public NotOrderableException(Long productId) {
        super(OrderErrorCode.NOT_ORDERABLE, OrderErrorCode.NOT_ORDERABLE.getMessage() + ": " + productId);
    }
}
