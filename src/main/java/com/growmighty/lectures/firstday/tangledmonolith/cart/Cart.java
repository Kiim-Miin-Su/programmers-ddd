package com.growmighty.lectures.firstday.tangledmonolith.cart;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Slf4j
@Entity
@Table(name = "carts")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CartItem> items = new ArrayList<>();

	@Column(nullable = false)
	private Long userId;

	public static Cart create(Long userId) {
		Cart cart = new Cart();
		cart.userId = userId;
		return cart;
	}

	/**
	 *
	 * @param productId CartItem이 가질 product 객체의 Id
	 * @param quantity  quantity <= 0 일 경우 IllegalException
	 */
	public void addItem(Long productId, int quantity) {

		if (quantity <= 0)
			throw new IllegalArgumentException("수량은 0 이하일 수 없습니다."); // Checklist01: 수량은 1 이상

		findItem(productId).ifPresentOrElse(
				// ifPresent
				cartItem -> cartItem.increaseQuantity(quantity),

				// ifNull
				() -> {
					CartItem cartItem = CartItem.create(productId, quantity);
					this.items.add(cartItem);
					cartItem.assignCart(this);
				}
		);
	} // end of addItem();

	public void changeQuantity(Long productId, int quantity) {
		CartItem cartItem = findItem(productId)
				.orElseThrow(() -> new IllegalArgumentException("장바구니에 해당 상품이 존재하지 않습니다: %s.".formatted(productId)));

		cartItem.changeQuantity(quantity);
	}

	/**
	 *
	 * @param productId 제거할 상품 ID
	 */
	public void removeItem(Long productId) {

		// FIXME: 참조되는 CartItem의 Cart 컬럼도 정리해야 함
//		if (!this.items.removeIf(cartItem -> cartItem.getProductId().equals(productId))) // 실패시 False 반환
//			throw new IllegalArgumentException("장바구니에 해당 상품이 존재하지 않습니다: %s.".formatted(productId));

		Iterator<CartItem> iterator = this.items.iterator(); // Iterator로 묶은 후 돌림.

		while (iterator.hasNext()) {
			CartItem cartItem = iterator.next();

			if (cartItem.getProductId().equals(productId)) { // 만약 상품이 존재한다면.
				iterator.remove(); // remove 되면 원본 list에서도 삭제됨.
				cartItem.assignCart(null); // 반대쪽 참조 또한 정리. (By GPT)
				return;
			}
		}

		throw new IllegalArgumentException("장바구니에 해당 상품이 존재하지 않습니다: %s.".formatted(productId)); // 만약 상품이 없으면.

	} // end of removeItem();

	/*
	============================
	======= 내부 유틸 함수 ========
	============================
	 */

	public Optional<CartItem> findItem(Long productId) {

		return this.items.stream()
				.filter(cartItem -> cartItem.getProductId().equals(productId))
				.findFirst();

	} // end of findItem();


	public void clear() {
		// FIXME: 반대편 참조도 함께 제거
		this.items.forEach(item -> item.assignCart(null)); // 하나씩 돌아가며 참조 제거. (By GPT)
		this.items.clear();
	}
}
