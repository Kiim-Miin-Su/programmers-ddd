package com.growmighty.lectures.firstday.tangledmonolith.homework;

import com.growmighty.lectures.firstday.tangledmonolith.product.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * [과제 DDD-2] 상품 재고 규칙 단위 테스트입니다.
 */
class ProductStockTest {

    @Test
    @DisplayName("재고 안에서 차감하면 재고가 줄어든다")
    void 재고를_차감한다() {
        Product product = Product.create(1L, "상품", BigDecimal.valueOf(1000), 10, "설명");

        product.decreaseStock(3);
        assertEquals(7, product.getStockQuantity());
        product.decreaseStock(7);

        assertEquals(0, product.getStockQuantity());
    }

    @Test
    @DisplayName("재고보다 많이 차감하면 예외가 나고 재고는 그대로다")
    void 재고_부족이면_예외() {
        Product product = Product.create(1L, "상품", BigDecimal.valueOf(1000), 10, "설명");

        assertThrows(IllegalStateException.class, () -> product.decreaseStock(11));

        assertEquals(10, product.getStockQuantity());
    }

    @Test
    @DisplayName("0 이하의 수량은 차감할 수 없다")
    void 수량이_0_이하면_예외() {
        Product product = Product.create(1L, "상품", BigDecimal.valueOf(1000), 10, "설명");

        for (int quantity : new int[]{0, -3}) {
            assertThrows(IllegalArgumentException.class, () -> product.decreaseStock(quantity));
            assertEquals(10, product.getStockQuantity());
        }
    }
}
