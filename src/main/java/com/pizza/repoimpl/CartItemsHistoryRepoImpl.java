package com.pizza.repoimpl;

import com.pizza.model.CartItemsHistory;
import com.pizza.repo.CartItemsHistoryRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class CartItemsHistoryRepoImpl implements CartItemsHistoryRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveSnapshot(Long cartId, Long foodId, double total, Long qty, boolean paid, Long customerId) {
        String sql = "INSERT INTO cart_items_history (cart_id, food_id, total, qty, paid, customer_id) VALUES (?, ?, ?, ?, ?, ?)";
        entityManager.createNativeQuery(sql)
                .setParameter(1, cartId)
                .setParameter(2, foodId)
                .setParameter(3, total)
                .setParameter(4, qty)
                .setParameter(5, paid)
                .setParameter(6, customerId)
                .executeUpdate();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<CartItemsHistory> findAllByCustomerId(Long customerId) {
        String sql = "SELECT * FROM cart_items_history WHERE customer_id = ? AND order_id IS NOT NULL";
        return entityManager.createNativeQuery(sql, CartItemsHistory.class)
                .setParameter(1, customerId)
                .getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public CartItemsHistory findById(Long id) {
        String sql = "SELECT * FROM cart_items_history WHERE id = ?";
        return (CartItemsHistory) entityManager.createNativeQuery(sql, CartItemsHistory.class)
                .setParameter(1, id)
                .getSingleResult();
    }

    @Override
    public void deleteById(Long id) {
        String sql = "Delete FROM cart_items_history WHERE id = ?";
        entityManager.createNativeQuery(sql)
                .setParameter(1, id)
                .executeUpdate();
    }
    
    @Override
    public List<CartItemsHistory> findByCartId(Long cartId) {
        String sql = "Select * from cart_items_history WHERE cart_id = :cartId";
        Query query = entityManager.createNativeQuery(sql, CartItemsHistory.class);
        query.setParameter("cartId", cartId);
        return query.getResultList();
    }
}
