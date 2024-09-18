package com.pizza.controller;

import com.pizza.model.CartItemsHistory;
import com.pizza.service.CartItemsHistoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carthistory")
public class CartItemsHistoryController {

    private CartItemsHistoryService cartItemsHistoryService;

    public CartItemsHistoryController(CartItemsHistoryService cartItemsHistoryService) {
		super();
		this.cartItemsHistoryService = cartItemsHistoryService;
	}

	@CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/snapshot")
    public void createSnapshot(@RequestParam Long cartId,
                               @RequestParam Long foodId,
                               @RequestParam double total,
                               @RequestParam Long qty,
                               @RequestParam boolean paid,
                               @RequestParam Long customerId) {
        cartItemsHistoryService.saveSnapshot(cartId, foodId, total, qty, paid, customerId);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/customer/{customerId}")
    public List<CartItemsHistory> getAllSnapshotsByCustomerId(@PathVariable Long customerId) {
        return cartItemsHistoryService.getAllSnapshotsByCustomerId(customerId);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{id}")
    public CartItemsHistory getSnapshotById(@PathVariable Long id) {
        return cartItemsHistoryService.getSnapshotById(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/{id}")
    public void deleteSnapshotById(@PathVariable Long id) {
        cartItemsHistoryService.deleteSnapshotById(id);
    }
    
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/cart/{cartid}")
    public List<CartItemsHistory> getAllByCartId(@PathVariable Long cartid) {
        return cartItemsHistoryService.findByCartId(cartid);
    }

    
}
