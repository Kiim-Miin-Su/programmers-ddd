package com.growmighty.lectures.firstday.tangledmonolith.product;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false) // 상품은 반드시 하나 이상의 seller를 가져야하므로 Product가 키를 가져야 함. (seller가 가질 경우 nullable)
	private Long sellerId; // sellerId만 있으면 join 가능

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private BigDecimal price = BigDecimal.ZERO;

	@Setter
	@Column(nullable = false)
	private Integer stockQuantity;

	@Lob
	private String description;

	public static Product create(long sellerId, String name, BigDecimal price, Integer stockQuantity, String description) {
		Product product = new Product();
		product.sellerId = sellerId;
		product.name = name;
		product.price = price;
		product.stockQuantity = stockQuantity;
		product.description = description;
		return product;
	}
}
