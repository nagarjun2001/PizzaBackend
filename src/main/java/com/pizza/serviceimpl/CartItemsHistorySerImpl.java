package com.pizza.serviceimpl;

import com.pizza.model.CartItemsHistory;
import com.pizza.repoimpl.CartItemsHistoryRepoImpl;
import com.pizza.service.CartItemsHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemsHistorySerImpl implements CartItemsHistoryService {

    @Autowired
    private CartItemsHistoryRepoImpl historyRepository;

    @Override
    public void saveSnapshot(Long cartId, Long foodId, double total, Long qty, boolean paid, Long customerId) {
        historyRepository.saveSnapshot(cartId, foodId, total, qty, paid, customerId);
    }

    @Override
    public List<CartItemsHistory> getAllSnapshotsByCustomerId(Long customerId) {
        return historyRepository.findAllByCustomerId(customerId);
    }

    @Override
    public CartItemsHistory getSnapshotById(Long id) {
        return historyRepository.findById(id);
    }

    @Override
    public void deleteSnapshotById(Long id) {
        historyRepository.deleteById(id);
    }

	@Override
	public List<CartItemsHistory> findByCartId(Long cartId) {
		return historyRepository.findByCartId(cartId);
	}
}
