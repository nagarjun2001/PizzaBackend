package com.pizza.repo;

import java.util.List;

import com.pizza.model.CartItemsHistory;

public interface CartItemsHistoryRepo {

//    public void saveCartItemsHistory(List<CartItemsHistory> historyList);
//    public List<CartItemsHistory> findHistoryByCustomerId(Long customerId);
//    public List<CartItemsHistory> findAll();
//    public CartItemsHistory findById(Long id);
//    public void deleteById(Long id);
	
	void saveSnapshot(Long cartId, Long foodId, double total, Long qty, boolean paid, Long customerId);
    List<CartItemsHistory> findAllByCustomerId(Long customerId);
    CartItemsHistory findById(Long id);
    void deleteById(Long id);
    List<CartItemsHistory> findByCartId(Long cartId);
}
