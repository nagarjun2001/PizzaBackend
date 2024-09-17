package com.pizza.repo;

import java.util.List;

import com.pizza.model.CartItems;

public interface SimCartItemsRepo {
	public void save(CartItems cartItems);
	public void update(CartItems cartItems);
	public CartItems findById(Long id);
	public void delete(Long id);
	public List<CartItems> findByCartId(Long cartId);
	public List<CartItems> findByCartIdAndFoodId(Long cartId, Long foodId);
	public List<CartItems> findByPaid();
	public List<CartItems> findByUnpaid();
	public List<CartItems> findByCustId(Long custid, Long cartid);
}
