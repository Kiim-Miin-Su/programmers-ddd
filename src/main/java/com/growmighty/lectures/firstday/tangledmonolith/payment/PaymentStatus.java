package com.growmighty.lectures.firstday.tangledmonolith.payment;

public enum PaymentStatus {
	READY, PAID, FAILED;

	public boolean canTransitionTo(PaymentStatus next) { // READY 에서만 바꿀 수 있음.
		return switch (this) {
			case READY -> next == PAID || next == FAILED; // READY면 next == PAID || next == FAILED 반환
			case PAID, FAILED -> false; // READY가 아니면 무조건 false
		};
	}
}
