package com.growmighty.lectures.firstday.tangledmonolith.common.exception;

public class InternalErrorException extends BusinessException {
	public InternalErrorException(String message) {
		super(ErrorCode.INTERNAL_ERROR, message);
	}
}
