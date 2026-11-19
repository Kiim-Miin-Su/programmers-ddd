package com.growmighty.lectures.firstday.tangledmonolith.homework;

import com.growmighty.lectures.firstday.tangledmonolith.payment.Payment;
import com.growmighty.lectures.firstday.tangledmonolith.payment.PaymentStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * [과제 DDD-2] 결제의 상태 전이와 승인 후 상태 변경 금지를 검증합니다.
 */
class PaymentStatusTest {

	@Test
	@DisplayName("준비(READY) 상태의 결제를 승인하면 PAID 가 된다")
	void 준비_상태를_승인한다() {
		Payment payment = Payment.ready(BigDecimal.valueOf(1000));
		assertEquals(PaymentStatus.READY, payment.getStatus());

		payment.transitionTo(PaymentStatus.PAID);

		assertEquals(PaymentStatus.PAID, payment.getStatus());
	}

	@Test
	@DisplayName("준비 상태의 결제를 실패 처리하면 FAILED 가 된다")
	void 준비_상태를_실패_처리한다() {
		Payment payment = Payment.ready(BigDecimal.valueOf(1000));
		assertEquals(PaymentStatus.READY, payment.getStatus());

		payment.transitionTo(PaymentStatus.FAILED);

		assertEquals(PaymentStatus.FAILED, payment.getStatus());
	}

	@Test
	@DisplayName("이미 승인된 결제는 다시 승인하거나 실패 처리할 수 없다")
	void 승인된_결제는_상태를_바꿀_수_없다() {
		Payment payment = Payment.ready(BigDecimal.valueOf(1000));
		payment.transitionTo(PaymentStatus.PAID);
		assertEquals(PaymentStatus.PAID, payment.getStatus());

		assertThrows(IllegalStateException.class, () -> payment.transitionTo(PaymentStatus.PAID));
		assertEquals(PaymentStatus.PAID, payment.getStatus());
		assertThrows(IllegalStateException.class, () -> payment.transitionTo(PaymentStatus.FAILED));
		assertEquals(PaymentStatus.PAID, payment.getStatus());
		assertThrows(IllegalStateException.class, () -> payment.transitionTo(PaymentStatus.READY));
		assertEquals(PaymentStatus.PAID, payment.getStatus());
	}
}
