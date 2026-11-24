package com.growmighty.lectures.firstday.tangledmonolith.payment.domain;

public enum PaymentStatus {
	READY,
	PAID,
	FAILED,
	CANCELLED;

	public boolean canTransitionTo(PaymentStatus next) {
		return switch (this) {
			case READY -> next == PAID || next == FAILED;
			case PAID -> next == CANCELLED;
			case FAILED, CANCELLED -> false;
		};
	}
}
