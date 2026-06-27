package com.growmighty.lectures.firstday.tangledmonolith.order;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(nullable = false)
    private String name;

    // 가격은 그냥 BigDecimal로 들고 있음.
    // 음수 가격도 그대로 들어가고, 비교할 때 equals 쓰면 1000 이랑 1000.00 이 다르다고 나온다.
    // 게다가 setter까지 열어놔서 Order 안 거치고 price 를 바로 바꿀 수 있음 (그럼 총액이랑 어긋남)
    @Setter
    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Integer quantity;

    public static OrderItem create(String name, BigDecimal price, Long productId, int quantity) {
        OrderItem orderItem = new OrderItem();
        orderItem.name = name;
        orderItem.price = price;
        orderItem.productId = productId;
        orderItem.quantity = quantity;

        return orderItem;
    }

    void assignOrder(Order order) {
        this.order = order;
    }
}
