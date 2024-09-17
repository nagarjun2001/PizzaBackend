package com.pizza.serviceimpl;

import org.springframework.stereotype.Service;

import com.pizza.model.Cart;
import com.pizza.repoimpl.CartRepoImpl;
import com.pizza.service.CartService;

@Service
public class CartSerImpl implements CartService{
	
	CartRepoImpl repo;

	public CartSerImpl(CartRepoImpl repo) {
		super();
		this.repo = repo;
	}

	@Override
	public Cart createCart() {
		Cart c = new Cart();
		repo.saveCart(c);
		return c;
	}

	@Override
	public Cart getCartById(Long cartId) {
		return repo.getCartById(cartId);
	}

	@Override
	public void deleteCart(Long cartId) {
		repo.deleteCart(cartId);
	}	

}