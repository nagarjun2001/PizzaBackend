package com.pizza.repo;

import java.util.List;

import com.pizza.model.CartItemsHistory;

public interface CartItemsHistoryRepo {
	void saveSnapshot(Long cartId, Long foodId, double total, Long qty, boolean paid, Long customerId);
    List<CartItemsHistory> findAllByCustomerId(Long customerId);
    CartItemsHistory findById(Long id);
    void deleteById(Long id);
    List<CartItemsHistory> findByCartId(Long cartId);
}
