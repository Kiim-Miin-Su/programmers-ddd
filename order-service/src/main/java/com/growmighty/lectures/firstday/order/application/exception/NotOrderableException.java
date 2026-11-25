package com.growmighty.lectures.firstday.order.application.exception;

public class NotOrderableException extends OrderException {

    public NotOrderableException() {
        super(OrderErrorCode.NOT_ORDERABLE, OrderErrorCode.NOT_ORDERABLE.getMessage());
    }
}
