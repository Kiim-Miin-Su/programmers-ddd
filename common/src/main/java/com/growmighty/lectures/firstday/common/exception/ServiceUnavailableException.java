package com.growmighty.lectures.firstday.common.exception;

/**
 * 협력 서비스가 일시적으로 응답하지 못할 때 던지는 예외.
 * "네 잘못도(400), 우리 버그도(500) 아니고, 잠시 후엔 될 거야(503)" 라는 정확한 신호다.
 */
public class ServiceUnavailableException extends BusinessException {

    public ServiceUnavailableException(String message) {
        super(ErrorCode.SERVICE_UNAVAILABLE, message);
    }
}
