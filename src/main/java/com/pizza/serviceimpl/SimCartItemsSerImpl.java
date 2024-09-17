package com.pizza.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pizza.model.CartItems;
import com.pizza.repoimpl.SimCartItemsRepoImpl;
import com.pizza.service.SimCartItemsService;

@Service
public class SimCartItemsSerImpl implements SimCartItemsService{

	SimCartItemsRepoImpl repo;

	public SimCartItemsSerImpl(SimCartItemsRepoImpl repo) {
		super();
		this.repo = repo;
	}

	@Override
	public void save(CartItems cartItems) {
		repo.save(cartItems);
	}

	@Override
	public void update(CartItems cartItems) {
		repo.update(cartItems);
	}

	@Override
	public CartItems findById(Long id) {
		return repo.findById(id);
	}

	@Override
	public void delete(Long id) {
		repo.delete(id);	
	}

	@Override
	public List<CartItems> findByCartId(Long cartId) {
		return repo.findByCartId(cartId);
	}

	@Override
	public List<CartItems> findByCartIdAndFoodId(Long cartId, Long foodId) {
		return repo.findByCartIdAndFoodId(cartId, foodId);
	}

	@Override
	public List<CartItems> findByPaid() {
		return repo.findByPaid();
	}
	
	@Override
	public List<CartItems> findByUnpaid() {
		return repo.findByUnpaid();
	}

	
}
