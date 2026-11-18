package com.growmighty.lectures.firstday.tangledmonolith;

import com.growmighty.lectures.firstday.tangledmonolith.order.Order;
import com.growmighty.lectures.firstday.tangledmonolith.order.OrderItem;
import com.growmighty.lectures.firstday.tangledmonolith.order.OrderRepository;
import com.growmighty.lectures.firstday.tangledmonolith.payment.Payment;
import com.growmighty.lectures.firstday.tangledmonolith.product.Product;
import com.growmighty.lectures.firstday.tangledmonolith.seller.Seller;
import com.growmighty.lectures.firstday.tangledmonolith.user.User;
import com.growmighty.lectures.firstday.tangledmonolith.user.UserRepository;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataJpaTest
class OrderRepositoryTests {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EntityManager entityManager;

	@Test
	@DisplayName("주문 저장 및 조회 테스트")
	void saveAndFindOrderTest() {
		User user = User.register("dannyseo@growmighty", "aaa33242", "Danny", "010-0000-0000");
		entityManager.persist(user);

		Seller seller = Seller.create(user.getId()); // create() has contented userId only
		entityManager.persist(seller);

		Product product = Product.create(seller.getId(), "Dofia 이동식 접이식 식탁 의자 4개 세트 가정용 소형주택 신축식", BigDecimal.valueOf(179000), 10, "test");
		entityManager.persist(product);

		List<OrderItem> items = new ArrayList<>();
		items.add(OrderItem.create(product.getId(), 1, product.getName(), product.getPrice()));

		Order order = Order.create(user.getId(), items);
		order.setTotalAmount(product.getPrice()); // 179000

		/*
		FIXME:
		 Payment까지 넣는 이유는 Order.paymentId가 필수값이어서, 상품 문제를 해결한 뒤에는 주문 저장에서 실패하기 때문입니다.
		 */
		Payment payment = Payment.ready(product.getPrice()); // 179000
		entityManager.persist(payment);
		order.assignPayment(payment.getId()); // set order's paymentId

		Order saved = orderRepository.save(order);
		entityManager.flush();
		entityManager.clear();

		Order found = orderRepository.findById(saved.getId()).orElseThrow();
		assertThat(found.getId()).isEqualTo(saved.getId());
		assertThat(found.getItems()).hasSize(1);
		OrderItem foundItem = found.getItems().getFirst(); // get(0) -> getFirst();
		assertThat(foundItem.getQuantity()).isEqualTo(1);
		assertThat(foundItem.getProductId()).isEqualTo(product.getId());
		assertThat(foundItem.getProductNameSnapshot()).isEqualTo(product.getName());
		assertThat(foundItem.getPriceSnapshot()).isEqualByComparingTo(product.getPrice());
	}
}
