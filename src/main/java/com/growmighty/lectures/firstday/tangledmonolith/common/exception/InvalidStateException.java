package com.growmighty.lectures.firstday.tangledmonolith.common.exception;

public class InvalidStateException extends BusinessException {
	public InvalidStateException(String message) {
		super(ErrorCode.INVALID_STATE, message);
	}
}
