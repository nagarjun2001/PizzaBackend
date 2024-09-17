package com.pizza.repoimpl;

import org.springframework.stereotype.Repository;

import com.pizza.model.Cart;
import com.pizza.repo.CartRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class CartRepoImpl implements CartRepo{

	EntityManager emanager;

	public CartRepoImpl(EntityManager emanager) {
		super();
		this.emanager = emanager;
	}

	@Override
	public void saveCart(Cart cart) {
		emanager.persist(cart);
	}

	@Override
	public void updateCart(Cart cart) {
		emanager.merge(cart);
	}

	@Override
	public Cart getCartById(Long id) {
		return emanager.find(Cart.class, id);
	}

	@Override
	public void deleteCart(Long id) {
		Cart cart = getCartById(id);
		emanager.remove(cart);
	}

	@Override
	public Cart getCartByCustId(Long custid) {
		String hql = "From Cart c where c.customer.id = :custid";
		Query q = emanager.createQuery(hql);
		q.setParameter("custid", custid);
		return (Cart) q.getSingleResult();
	}

}