package com.pizza.repoimpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pizza.model.CartItems;
import com.pizza.repo.CartItemsRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class CartItemsRepoImpl implements CartItemsRepo{

	EntityManager emanager;

	public CartItemsRepoImpl(EntityManager emanager) {
		super();
		this.emanager = emanager;
	}

	@Override
	public void save(CartItems cartItems) {
		emanager.persist(cartItems);
	}

	@Override
	public void update(CartItems cartItems) {
		emanager.merge(cartItems);
	}

	@Override
	public CartItems findById(Long id) {
		return emanager.find(CartItems.class, id);
	}

	@Override
	public void delete(Long id) {
		CartItems delobj = findById(id);
		emanager.remove(delobj);
	}

	@Override
	public List<CartItems> findByCartId(Long cartId) {
		String hql = "FROM CartItems ci WHERE ci.cart.id = :cartId";
        Query query = emanager.createQuery(hql);
        query.setParameter("cartId", cartId);
        return query.getResultList();
	}

	@Override
	public CartItems findByCartIdAndFoodId(Long cartId, Long foodId) {
		String hql = "FROM CartItems ci WHERE ci.cart.id = :cartId AND ci.food.id = :foodId";
        TypedQuery<CartItems> q = emanager.createQuery(hql, CartItems.class);
        q.setParameter("cartId", cartId);
        q.setParameter("foodId", foodId);
        return q.getSingleResult();
	}

	
}