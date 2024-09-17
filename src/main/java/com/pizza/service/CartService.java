package com.pizza.service;

import com.pizza.model.Cart;

public interface CartService {

	public Cart createCart();
	public Cart getCartById(Long cartId);
    public void deleteCart(Long cartId);
}
