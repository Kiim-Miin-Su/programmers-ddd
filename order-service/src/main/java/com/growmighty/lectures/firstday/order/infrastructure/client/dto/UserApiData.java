package com.growmighty.lectures.firstday.order.infrastructure.client.dto;

public record UserApiData(
    Long id,
    String email,
    String name,
    String phoneNumber
) {
}
