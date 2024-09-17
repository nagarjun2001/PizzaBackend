package com.pizza.repo;

import java.util.List;

import com.pizza.model.CartItems;

public interface CartItemsRepo {

	public void save(CartItems cartItems);
	public void update(CartItems cartItems);
	public CartItems findById(Long id);
	public void delete(Long id);
	public List<CartItems> findByCartId(Long cartId);
	public CartItems findByCartIdAndFoodId(Long cartId, Long foodId);
}
