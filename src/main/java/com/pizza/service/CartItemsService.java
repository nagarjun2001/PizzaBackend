package com.pizza.service;

import java.util.List;

import com.pizza.model.CartItems;

public interface CartItemsService {

	public void addItemToCart(Long cartId, Long foodId, Long quantity);
	public void updateItemQuantity(Long cartItemId, Long quantity);
	public void removeItemFromCart(Long cartItemId);
	public List<CartItems> getCartItemsByCartId(Long cartId);
}
