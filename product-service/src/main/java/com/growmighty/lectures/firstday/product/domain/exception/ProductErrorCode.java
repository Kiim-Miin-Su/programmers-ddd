package com.growmighty.lectures.firstday.product.domain.exception;

import lombok.Getter;

@Getter
public enum ProductErrorCode {
    NOT_FOUND("P001", "Product not found"),
    NOT_ORDERABLE("P002", "Not orderable"),
    NOT_ENOUGH_STOCK("P003", "Not enough stock"),
    UNKNOWN_ERROR("P099", "Unknown error");

    private final String code;
    private final String message;

    ProductErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
