package com.growmighty.lectures.firstday.order.infrastructure.client.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponseBody<T>(boolean success, T data, ErrorBody error) {

    public record ErrorBody(String code, String message) {
    }
}
