package com.growmighty.lectures.firstday.tangledmonolith.payment;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "payments")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private BigDecimal amount;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private PaymentStatus status;

	public static Payment ready(BigDecimal amount) {
		Payment payment = new Payment();
		payment.amount = amount;
		payment.status = PaymentStatus.READY;
		return payment;
	}

	public void transitionTo(PaymentStatus next) {
		if (!this.status.canTransitionTo(next)) { // READY인지 검사 -> next == PAID || next == FAILED 검사
			throw new IllegalStateException("결제 상태는 READY에서만 전이가 가능합니다: %s -> %s".formatted(this.status, next));
		}
	}

//    public void changeStatus(PaymentStatus status) {
//        this.status = status; checklist03: changeStatus 삭제
//    }
}
