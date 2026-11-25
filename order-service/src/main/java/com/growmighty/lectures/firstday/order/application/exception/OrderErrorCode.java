package com.growmighty.lectures.firstday.order.application.exception;

import lombok.Getter;

@Getter
public enum OrderErrorCode {
    NOT_FOUND_PRODUCT("C001", "상품이 존재하지 않습니다."),
    NOT_ENOUGH_STOCK("C002", "재고가 부족합니다."),
    NOT_ORDERABLE("C003", "주문할 수 없는 상품입니다."),
    UNKNOWN_ERROR("C099", "알 수 없는 오류입니다.");

    private final String code;
    private final String message;

    OrderErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
