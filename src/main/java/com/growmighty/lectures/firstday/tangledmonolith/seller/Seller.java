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

	@OneToMany(mappedBy = "seller")
	private List<Product> products = new ArrayList<>();

	@Column(nullable = false)
	private Long userId;

	public static Seller create(long userId) {
		Seller seller = new Seller();
		seller.userId = userId;

		return seller;
	}
}
