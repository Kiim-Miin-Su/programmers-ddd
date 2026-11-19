package com.growmighty.lectures.firstday.tangledmonolith.product;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Long sellerId;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private BigDecimal price;

	@Column(nullable = false)
	private Integer stockQuantity;

	@Lob
	private String description;

	public static Product create(Long sellerId, String name, BigDecimal price, Integer stockQuantity, String description) {
		Product product = new Product();
		product.sellerId = sellerId;
		product.name = name;
		product.price = price;
		product.stockQuantity = stockQuantity;
		product.description = description;
		return product;
	}

	/**
	 *
	 * @param quantity 1 이상의 int값.
	 */
	public void decreaseStock(int quantity) {
		if (this.stockQuantity < quantity) {
			throw new IllegalStateException("재고가 부족합니다. product=" + this.name);
		}
		if (this.stockQuantity - quantity <= 0) {
			// Checklist02: 0이하 수량 거부
			throw new IllegalArgumentException("Quantity must be greater than zero"); // 자신의 존재에 관한 규칙이므로 Entity에 두는 것이 적합.
		}

		this.stockQuantity = (this.stockQuantity - quantity);
	}
}
