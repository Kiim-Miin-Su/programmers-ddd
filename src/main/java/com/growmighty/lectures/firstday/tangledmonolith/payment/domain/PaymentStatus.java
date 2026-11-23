package com.growmighty.lectures.firstday.tangledmonolith.payment.domain;

public enum PaymentStatus {
	READY,
	PAID,
	FAILED,
	CANCELLED;

	public boolean canTransitionTo(PaymentStatus next) {
		return switch (this) {
			case READY -> next == PAID || next == CANCELLED || next == FAILED;
			case PAID, FAILED, CANCELLED -> false;
		};
	}
}
