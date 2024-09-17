package com.pizza.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pizza.model.Cart;
import com.pizza.model.CartItems;
import com.pizza.model.CartItemsHistory;
import com.pizza.repoimpl.CartItemsHistoryRepoImpl;
import com.pizza.serviceimpl.CartItemsHistorySerImpl;
import com.pizza.serviceimpl.SimpleCartSerImpl;

@RestController
@RequestMapping("/simcart")
@CrossOrigin("http://localhost:3000")
public class SimCartController {

	SimpleCartSerImpl service;
	CartItemsHistorySerImpl historyservice;
	CartItemsHistoryRepoImpl repo;

	public SimCartController(SimpleCartSerImpl service, CartItemsHistorySerImpl historyservice,
			CartItemsHistoryRepoImpl repo) {
		super();
		this.service = service;
		this.historyservice = historyservice;
		this.repo = repo;
	}

	String s = "Success";
	String f = "Failure";
	
	@CrossOrigin("http://localhost:3000")
	@PostMapping("/create")
	public String saveCart(@RequestBody Cart cart) {
		String msg = "";
		try {
	        Cart existingCart = service.getCartByCustId(cart.getCustomer().getId());
	        if (existingCart == null) {
	            service.saveCart(cart);
	        }
	        else {
	            msg = "Cart already exists for this customer";
	        }
		}
    	catch (Exception e) {
    		e.printStackTrace();
    		msg = f;
    	}
        return msg;	    
	}
	
	@CrossOrigin("http://localhost:3000")
	@GetMapping("/cart/{cartid}")
    public Cart getCart(@PathVariable Long cartid) {
        return service.getCartById(cartid);
    }

	@CrossOrigin("http://localhost:3000")
    @DeleteMapping("/{cartid}")
    public String deleteCartById(@PathVariable Long cartid) {
    	String msg = "";
    	try {
    		service.deleteCart(cartid);
    		msg = s;
    	}
    	catch (Exception e) {
    		msg = f;
    	}
        return msg;
    }
    
	@CrossOrigin("http://localhost:3000")
    @PutMapping
    public String updateCart(@RequestBody Cart cart) {
    	String msg = "";
    	try {
            Cart existingCart = service.getCartById(cart.getId());
            if (existingCart != null) {
                existingCart.setTotal(cart.getTotal());

                if (existingCart.getCartiem() == null) {
                    existingCart.setCartiem(new ArrayList<>());
                }
                
                existingCart.getCartiem().clear();
                for (CartItems item : cart.getCartiem()) {
                    item.setCart(existingCart);
                    existingCart.getCartiem().add(item);
                }
                
                service.updateCart(existingCart);
                msg = s;
            } else {
                msg = f+" Cart not found";
            }
        } catch (Exception e) {
            msg = f;
            e.printStackTrace();
        }
        return msg;
    }
    
	@CrossOrigin("http://localhost:3000")
    @GetMapping("/customer/{custid}")
    public Cart getCartByCustId(@PathVariable Long custid) {
    		return service.getCartByCustId(custid);
    }
    
//    @PutMapping("/markaspaid/{cartId}")
//    public String markItemsAsPaid(@PathVariable Long cartId) {
//        try {
//            historyservice.markItemsAsPaid(cartId);
//            return "Success";
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "Failure";
//        }
//    }

//    @GetMapping("/history/customer/{customerId}")
//    public List<CartItemsHistory> getHistoryByCustomerId(@PathVariable Long customerId) {
//        return historyservice.getHistoryByCustomerId(customerId);
//    }
    
//    @PostMapping("/copy/{sourceCartId}")
//    public String copyCartItems(@PathVariable Long sourceCartId) {
//        try {
//            Cart sourceCart = service.getCartById(sourceCartId);
//            if (sourceCart == null) {
//                return "Source cart not found";
//            }
//
//            // Create a new CartItemsHistory entity to hold the copied items
//            CartItemsHistory newCartHistory = new CartItemsHistory();
//            newCartHistory.setTotal(sourceCart.getTotal()); // If total is relevant in history
//
//            // List to hold the copied CartItemsHistory entries
//            List<CartItemsHistory> copiedItems = new ArrayList<>();
//
//            for (CartItems item : sourceCart.getCartiem()) {
//                CartItemsHistory newItemHistory = new CartItemsHistory();
//                newItemHistory.setFoodId(item.getFood()); // Assuming CartItemsHistory has a Food property
//                newItemHistory.setQty(item.getQty());   // Assuming CartItemsHistory has a Qty property
//                // You may need to set other properties relevant to CartItemsHistory
//                copiedItems.add(newItemHistory);
//            }
//
//            // Save each CartItemsHistory item
//            historyservice.saveCartItemsHistory(copiedItems);
//
//            return "Cart items copied successfully";
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "Failed to copy cart items";
//        }
//    }


}
