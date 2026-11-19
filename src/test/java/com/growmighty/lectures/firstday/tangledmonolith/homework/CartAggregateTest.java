package com.growmighty.lectures.firstday.tangledmonolith.homework;

import com.growmighty.lectures.firstday.tangledmonolith.cart.Cart;
import com.growmighty.lectures.firstday.tangledmonolith.cart.CartItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * [과제 DDD-2] 장바구니 애그리거트 불변식 단위 테스트입니다.
 */
class CartAggregateTest {

    @Test
    @DisplayName("새 상품을 담으면 항목이 하나 추가된다")
    void 새_상품을_담으면_항목이_추가된다() {
        Cart cart = Cart.create(1L);
        assertTrue(cart.getItems().isEmpty());

        cart.addItem(2L, 2);

        assertEquals(1, cart.getItems().size());
        CartItem item = cart.getItems().get(0);
        assertEquals(2L, item.getProductId());
        assertEquals(2, item.getQuantity());
        assertSame(cart, item.getCart());
    }

    @Test
    @DisplayName("같은 상품을 다시 담으면 새 항목이 아니라 수량이 합쳐진다")
    void 같은_상품을_담으면_수량이_합쳐진다() {
        Cart cart = Cart.create(1L);
        cart.addItem(2L, 2);
        CartItem originalItem = cart.getItems().get(0);

        cart.addItem(2L, 3);

        assertEquals(1, cart.getItems().size());
        assertSame(originalItem, cart.getItems().get(0));
        assertEquals(5, originalItem.getQuantity());
    }

    @Test
    @DisplayName("수량이 0 이하이면 담을 수 없다")
    void 수량이_0_이하면_예외() {
        Cart cart = Cart.create(1L);

        assertThrows(IllegalArgumentException.class, () -> cart.addItem(2L, 0));
        assertThrows(IllegalArgumentException.class, () -> cart.addItem(2L, -1));
        assertTrue(cart.getItems().isEmpty());
    }

    @Test
    @DisplayName("담긴 상품의 수량을 바꿀 수 있다")
    void 수량을_변경한다() {
        Cart cart = Cart.create(1L);
        cart.addItem(2L, 2);

        cart.changeQuantity(2L, 4);

        assertEquals(1, cart.getItems().size());
        assertEquals(4, cart.getItems().get(0).getQuantity());
    }

    @Test
    @DisplayName("장바구니에 없는 상품의 수량을 바꾸면 예외가 난다")
    void 없는_상품의_수량_변경은_예외() {
        Cart cart = Cart.create(1L);
        cart.addItem(2L, 2);

        assertThrows(IllegalArgumentException.class, () -> cart.changeQuantity(3L, 4));

        assertEquals(1, cart.getItems().size());
        assertEquals(2, cart.getItems().get(0).getQuantity());
    }

    @Test
    @DisplayName("담긴 상품을 뺄 수 있다")
    void 상품을_뺀다() {
        Cart cart = Cart.create(1L);
        cart.addItem(2L, 2);
        cart.addItem(3L, 1);
        CartItem removedItem = cart.findItem(2L).orElseThrow();

        cart.removeItem(2L);

        assertEquals(1, cart.getItems().size());
        assertTrue(cart.findItem(2L).isEmpty());
        assertEquals(3L, cart.getItems().get(0).getProductId());
        assertSame(cart, cart.getItems().get(0).getCart());
        assertNull(removedItem.getCart());
    }
}
