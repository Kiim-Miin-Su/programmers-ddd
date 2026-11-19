package com.growmighty.lectures.firstday.tangledmonolith.cart;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
		validateQuantity(quantity);

		CartItem cartItem = new CartItem();
		// FIXME: product를 참조하지 않으면 만약 **product의 재고가 부족해도** 장바구니에 들어갈 CartItem을 create 하는 거 아님?
		cartItem.productId = productId;
		cartItem.quantity = quantity;

		return cartItem;
	}

	public void changeQuantity(Integer quantity) {
		validateQuantity(quantity);
		this.quantity = quantity;
	}

	public void increaseQuantity(Integer amount) {
		validateQuantity(amount);
		this.quantity = Math.addExact(this.quantity, amount);
	}

	/*
	===============================
	========= 내부 유틸 함수 =========
	===============================
	 */

	private static void validateQuantity(Integer quantity) {
		if (quantity <= 0)
			throw new IllegalArgumentException("Quantity must be greater than zero"); // 자신의 존재에 관한 규칙이므로 Entity에 두는 것이 좋을 것 같음.
	}

	void assignCart(Cart cart) {
		this.cart = cart;
	}
}
