package com.growmighty.lectures.firstday.tangledmonolith.cart.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "carts")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart {
	public static final int MAX_DISTINCT_ITEMS = 50;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CartItem> items = new ArrayList<>();

	@Column(nullable = false)
	private Long userId;

	private Cart(Long userId) {
		this.userId = userId;
	}

	public static Cart create(Long userId) {
		return new Cart(userId);
	}

	public void addItem(Long productId, int quantity) {

		if (items.size() >= MAX_DISTINCT_ITEMS) {
			throw new IllegalStateException("장바구니에는 최대 " + MAX_DISTINCT_ITEMS + "종류까지 담을 수 있습니다.");
		}

		if (quantity <= 0) {
			throw new IllegalArgumentException("수량은 0보다 커야합니다.");
		}

		this.findItem(productId).ifPresentOrElse(
				// present
				item -> item.addQuantity(quantity),
				//else
				() -> {
					CartItem item = CartItem.create(productId, quantity);
					item.assignCart(this);
					this.items.add(item);
				}
		);
	}

	public void changeQuantity(Long productId, int newQuantity) {
		this.findItem(productId).ifPresentOrElse(
				// present
				item -> item.changeQuantity(newQuantity),
				// else
				() -> {
					throw new IllegalArgumentException("Item not found: %s".formatted(productId));
				});
	}

	public void removeItem(Long productId) {
		this.findItem(productId).ifPresentOrElse(
				// present
				item -> {
					this.items.remove(item);
					item.assignCart(null);
				},
				// else
				() -> {
					throw new IllegalArgumentException("Item not found: %s".formatted(productId));
				});
	}

	public void clear() {
		this.items.forEach(item -> item.assignCart(null));
		this.items.clear();
	}

	private Optional<CartItem> findItem(Long productId) {
		return items.stream().filter(item -> item.hasProduct(productId)).findFirst();
	}

}
