package com.pizza.service;

import java.util.List;

import com.pizza.model.CartItemsHistory;

public interface CartItemsHistoryService {
	void saveSnapshot(Long cartId, Long foodId, double total, Long qty, boolean paid, Long customerId);
    List<CartItemsHistory> getAllSnapshotsByCustomerId(Long customerId);
    CartItemsHistory getSnapshotById(Long id);
    void deleteSnapshotById(Long id);
    List<CartItemsHistory> findByCartId(Long cartId);

}
