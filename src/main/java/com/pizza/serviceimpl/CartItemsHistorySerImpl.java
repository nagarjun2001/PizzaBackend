package com.pizza.serviceimpl;

//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.springframework.stereotype.Service;
//
//import com.pizza.model.Cart;
//import com.pizza.model.CartItemsHistory;
//import com.pizza.repoimpl.CartItemsHistoryRepoImpl;
//import com.pizza.repoimpl.CartRepoImpl;
//import com.pizza.service.CartItemsHistoryService;
//
//@Service
//public class CartItemsHistorySerImpl implements CartItemsHistoryService{
//
//	CartRepoImpl cartrepo;
//	CartItemsHistoryRepoImpl repo;
//	
//	public CartItemsHistorySerImpl(CartRepoImpl cartrepo, CartItemsHistoryRepoImpl repo) {
//		super();
//		this.cartrepo = cartrepo;
//		this.repo = repo;
//	}
//
//	public void markItemsAsPaid(Long cartId) {
//        Cart cart = cartrepo.getCartById(cartId);
//        if(cart == null) {
//        	throw new RuntimeException("Cart not found");
//        }
//
//        List<CartItemsHistory> historyList = cart.getCartiem().stream()
//                .map(item -> new CartItemsHistory(
//                        cart.getId(),
//                        item.getFood().getId(),
//                        item.getTotal(),
//                        item.getQty(),
//                        true
//                ))
//                .collect(Collectors.toList());
//
//        repo.saveCartItemsHistory(historyList);
//    }
//
//    public List<CartItemsHistory> getHistoryByCustomerId(Long customerId) {
//        return repo.findHistoryByCustomerId(customerId);
//    }
//    
//    
//}

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
