package com.growmighty.lectures.firstday.tangledmonolith.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PaymentService {
	private final PaymentRepository paymentRepository;

	public Payment create(BigDecimal totalAmount) {
//		Payment payment = Payment.ready(totalAmount);
//        payment.changeStatus(PaymentStatus.PAID); checklist03 remove changeStatus
		return paymentRepository.save(Payment.ready(totalAmount));
	}

	public void approve(Long paymentId) {
		Payment payment = this.paymentRepository.findById(paymentId)
				.orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 결제 요청입니다: %s".formatted(paymentId)));

		payment.transitionTo(PaymentStatus.PAID);
	}

	public void fail(Long paymentId) {
		Payment payment = this.paymentRepository.findById(paymentId)
				.orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 결제 요청입니다: %s".formatted(paymentId)));

		payment.transitionTo(PaymentStatus.FAILED);
	}
}
