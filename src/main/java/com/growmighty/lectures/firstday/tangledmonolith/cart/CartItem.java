package com.growmighty.lectures.firstday.tangledmonolith.cart;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Table(name = "cart_items")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "cart_id")
	private Cart cart;

	@Column(nullable = false)
	private Long productId;

	@Column(nullable = false)
	private Integer quantity;

	/*
	===========================
	======= ENTITY RULE =======
	===========================
	 */

	public static CartItem create(Long productId, int quantity) {
		if (quantity <= 0) throw new IllegalArgumentException("Quantity must be greater than zero");

		CartItem cartItem = new CartItem();
		cartItem.productId = productId;
		cartItem.quantity = quantity;

		return cartItem;
	}

	public void changeQuantity(Integer quantity) {
		if (quantity <= 0)
			throw new IllegalArgumentException("Quantity must be greater than zero"); // 자신의 존재에 관한 규칙이므로 Entity에 두는 것이 좋을 것 같음.

		this.quantity = quantity;
	}

	void assignCart(Cart cart) {
		this.cart = cart;
	}
}
