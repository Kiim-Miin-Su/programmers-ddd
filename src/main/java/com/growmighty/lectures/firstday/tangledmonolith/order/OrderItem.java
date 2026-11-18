package com.growmighty.lectures.firstday.tangledmonolith.order;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "order_id")
	private Order order;

	@Column(nullable = false)
	private Long productId; // Product reference removed

	// snapshot을 통한 product 변경 대응
	@Column(nullable = false)
	private String productNameSnapshot; // snapshot added

	@Column(nullable = false)
	private BigDecimal priceSnapshot = BigDecimal.ZERO; // snapshot added

	@Column(nullable = false)
	private Integer quantity;

	public static OrderItem create(long productId, int quantity, String productNameSnapshot, BigDecimal priceSnapshot) {
		OrderItem orderItem = new OrderItem();

		orderItem.productId = productId;
		orderItem.quantity = quantity;
		orderItem.productNameSnapshot = productNameSnapshot;
		orderItem.priceSnapshot = priceSnapshot;

		return orderItem;
	}

	void assignOrder(Order order) {
		this.order = order;
	}
}
