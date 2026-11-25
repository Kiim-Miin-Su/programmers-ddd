package com.growmighty.lectures.firstday.product.domain.exception;

import lombok.Getter;

@Getter
public class ProductException extends RuntimeException {

    private final ProductErrorCode errorCode;

    public ProductException() {
        super(ProductErrorCode.UNKNOWN_ERROR.getMessage());
        this.errorCode = ProductErrorCode.UNKNOWN_ERROR;
    }

    public ProductException(ProductErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ProductException(ProductErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
