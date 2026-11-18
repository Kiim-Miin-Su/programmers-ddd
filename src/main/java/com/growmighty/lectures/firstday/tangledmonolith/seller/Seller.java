package com.growmighty.lectures.firstday.tangledmonolith.seller;

import com.growmighty.lectures.firstday.tangledmonolith.product.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sellers")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Seller {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/*
	양방향 참조 끊어내기; product가 seller_id를 가짐으로써 join이 가능합니다.
	 */
//	@OneToMany(mappedBy = "seller")
//	private List<Product> products = new ArrayList<>();

	@Column(nullable = false)
	private Long userId; // User reference removed

	public static Seller create(long userId) {
		Seller seller = new Seller();
		seller.userId = userId;

		return seller;
	}
}
