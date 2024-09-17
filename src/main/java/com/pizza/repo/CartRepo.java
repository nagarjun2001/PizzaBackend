package com.pizza.repo;

import com.pizza.model.Cart;

public interface CartRepo {
	
	public void saveCart(Cart cart);
	public void updateCart(Cart cart);
    public Cart getCartById(Long id);
    public Cart getCartByCustId(Long custid);
    public void deleteCart(Long id);
}
