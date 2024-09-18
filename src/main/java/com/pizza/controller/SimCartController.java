package com.pizza.controller;

import java.util.ArrayList;

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
import com.pizza.repo.CartItemsHistoryRepo;
import com.pizza.service.CartItemsHistoryService;
import com.pizza.service.SimpleCartService;

@RestController
@RequestMapping("/simcart")
@CrossOrigin("http://localhost:3000")
public class SimCartController {

	SimpleCartService service;
	CartItemsHistoryService historyservice;
	CartItemsHistoryRepo repo;

	public SimCartController(SimpleCartService service, CartItemsHistoryService historyservice,
			CartItemsHistoryRepo repo) {
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
        }
        return msg;
    }
    
	@CrossOrigin("http://localhost:3000")
    @GetMapping("/customer/{custid}")
    public Cart getCartByCustId(@PathVariable Long custid) {
    		return service.getCartByCustId(custid);
    }
	
}
