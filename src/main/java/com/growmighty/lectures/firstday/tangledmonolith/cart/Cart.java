package com.growmighty.lectures.firstday.tangledmonolith.cart;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// orphanRemoval: 관련 테이블 (cart item) 함께 삭제
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CartItem> items = new ArrayList<>();

	//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "user_id")
	@Column(nullable = false)
	private Long userId;

	/**
	 * userId를 받아 repository에 저장할 Cart 객체를 만들어줍니다.
	 *
	 * @param userId 회원 간의 연결을 userId로 이어 약한 결합 유도
	 * @return userId를 포함한 Cart 객체
	 */
	public static Cart create(Long userId) { // User 객체를 받게 되면 compile 시에 의존성이 생겨버림
		Cart cart = new Cart();
//		cart.userId = user.getId();
//		클라이언트가 보낸 요청을 인증 정보에서 가져올 필요 있음. by GPT
		cart.userId = userId;

		return cart;
	}

	/**
	 * 추후 item 검증 로직을 여기에 구현합니다. 애그리거트 루트
	 *
	 * @param item 추가할 item
	 */
	public void addItem(CartItem item) {
		this.items.add(item);

		if (item.getCart() != this) {
			item.assignCart(this);
		}
	}

	public void clear() {
		this.items.clear();
	}
}
