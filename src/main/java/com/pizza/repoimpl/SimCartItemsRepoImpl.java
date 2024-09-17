package com.pizza.repoimpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pizza.model.CartItems;
import com.pizza.repo.SimCartItemsRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class SimCartItemsRepoImpl implements SimCartItemsRepo{

	EntityManager emanager;
	
	public SimCartItemsRepoImpl(EntityManager emanager) {
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
		CartItems delobj = (CartItems) findById(id);
		emanager.remove(delobj);
	}

	@Override
	public List<CartItems> findByCartId(Long cartId) {
		String hql = "From CartItems ci where ci.cart.id = :cartId";
        Query query = emanager.createQuery(hql);
        query.setParameter("cartId", cartId);
        return query.getResultList();
	}

	@Override
	public List<CartItems> findByCartIdAndFoodId(Long cartId, Long foodId) {
		String hql = "From CartItems ci where ci.cart.id = :cartId AND ci.food.id = :foodId";
        Query q = emanager.createQuery(hql, CartItems.class);
        q.setParameter("cartId", cartId);
        q.setParameter("foodId", foodId);
        return q.getResultList();
	}

	@Override
	public List<CartItems> findByPaid() {
		String hql = "From CartItems ci where ci.ispaid = true";
		Query q = emanager.createQuery(hql);
		return q.getResultList();
	}

	@Override
	public List<CartItems> findByUnpaid() {
		String hql = "From CartItems ci where ci.ispaid = false";
		Query q = emanager.createQuery(hql);
		return q.getResultList();
	}

	@Override
	public List<CartItems> findByCustId(Long custid, Long cartid) {
		String hql = "From CartItems ci where ci.cart.id = :cartid AND ci.customer.id = :custid";
        Query q = emanager.createQuery(hql, CartItems.class);
        q.setParameter("cartid", cartid);
        q.setParameter("custid", custid);
        return q.getResultList();
	}
	
	

}
