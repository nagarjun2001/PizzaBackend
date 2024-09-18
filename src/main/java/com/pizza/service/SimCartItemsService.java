package com.pizza.service;

import java.util.List;

import com.pizza.model.CartItems;

public interface SimCartItemsService {
	public void save(CartItems cartItems);
	public void update(CartItems cartItems);
	public CartItems findById(Long id);
	public void delete(Long id);
	public List<CartItems> findByCartId(Long cartId);
	public List<CartItems> findByCartIdAndFoodId(Long cartId, Long foodId);
	public List<CartItems> findByPaid();
	public List<CartItems> findByUnpaid();
}
