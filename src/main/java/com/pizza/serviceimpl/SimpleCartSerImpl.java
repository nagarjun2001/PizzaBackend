package com.pizza.serviceimpl;

import org.springframework.stereotype.Service;

import com.pizza.model.Cart;
import com.pizza.repoimpl.SimpleCartRepoImpl;
import com.pizza.service.SimpleCartService;

@Service
public class SimpleCartSerImpl implements SimpleCartService {
	
	SimpleCartRepoImpl repo;

	public SimpleCartSerImpl(SimpleCartRepoImpl repo) {
		super();
		this.repo = repo;
	}

	@Override
	public void saveCart(Cart cart) {
		repo.saveCart(cart);
	}

	@Override
	public void updateCart(Cart cart) {
		repo.updateCart(cart);
	}

	@Override
	public Cart getCartById(Long id) {
		return repo.getCartById(id);
	}

	@Override
	public Cart getCartByCustId(Long custid) {
		return repo.getCartByCustId(custid);
	}

	@Override
	public void deleteCart(Long id) {
		repo.deleteCart(id);
	}

}
