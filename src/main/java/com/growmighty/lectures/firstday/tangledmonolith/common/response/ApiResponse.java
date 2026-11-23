package com.growmighty.lectures.firstday.tangledmonolith.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.growmighty.lectures.firstday.tangledmonolith.common.exception.ErrorCode;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(boolean success, T data, ApiError error) {

	public static <T> ApiResponse<T> ok(T data) {
		return new ApiResponse<>(true, data, null);
	}

	public static ApiResponse<Void> ok() {
		return new ApiResponse<>(true, null, null);
	}

	public static ApiResponse<Void> fail(ApiError error) {
		return new ApiResponse<>(false, null, error);
	}

	public record ApiError(ErrorCode error, String message) {
	}
}
