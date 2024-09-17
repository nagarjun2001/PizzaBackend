package com.pizza.controller;

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

import com.pizza.model.CartItems;
import com.pizza.serviceimpl.SimCartItemsSerImpl;

@RestController
@RequestMapping("/simcartitems")
@CrossOrigin("http://localhost:3000")
public class SimCartItemsController {

	SimCartItemsSerImpl service;
	
	public SimCartItemsController(SimCartItemsSerImpl service) {
		super();
		this.service = service;
	}

	String s = "Success";
	String f = "Failure";
	
	@CrossOrigin("http://localhost:3000")
	@PostMapping
	public String addToCart(@RequestBody CartItems citem) {
		String msg = "";
    	try {
    		service.save(citem);
    		msg = s;
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    		msg = f;
    	}
        return msg;
    }
	
	@CrossOrigin("http://localhost:3000")
	@PutMapping("/{id}")
    public String updateCartItems(@PathVariable("id") Long id, @RequestBody CartItems ci) {
        try {
            CartItems existingItem = service.findById(id);
            if (existingItem == null) {
                return "Item not found";
            }
            existingItem.setQty(ci.getQty());
            existingItem.setTotal(ci.getTotal());
            service.update(existingItem);
            return s;
        } catch (Exception e) {
            return f;
        }
    }
	
	@CrossOrigin("http://localhost:3000")
	@GetMapping("/cart/{cartid}")
	public List<CartItems> getCartItemsByCartId(@PathVariable("cartid") Long cartid) {
		return service.findByCartId(cartid);
	}
	
	@CrossOrigin("http://localhost:3000")
	@GetMapping("/{cartitemid}")
	public CartItems getCartItemsByCartItemId(@PathVariable("cartitemid") Long cartitemid) {
		return service.findById(cartitemid);
	}
	
	@CrossOrigin("http://localhost:3000")
	@GetMapping("cart/{cartid}/food/{foodid}")
	public List<CartItems> getCartItemsByCartIdandFoodid(@PathVariable("cartid") Long cartitemid,@PathVariable("foodid") Long foodid) {
		return service.findByCartIdAndFoodId(cartitemid, foodid);
	}
	
	@CrossOrigin("http://localhost:3000")
	@DeleteMapping("/{id}")
	public String deleteByCartItemId(@PathVariable("id") Long id) {
		String msg = "";
    	try {
    		service.delete(id);
    		msg = s;
    	}
    	catch (Exception e) {
    		msg = f;
    	}
        return msg;
	}
	
	@CrossOrigin("http://localhost:3000")
	@GetMapping("/paid")
    public List<CartItems> findByPaid(){
    	return service.findByPaid();
    }
	
	@CrossOrigin("http://localhost:3000")
	@GetMapping("/unpaid")
	public List<CartItems> findByUnpaid(){
		return service.findByUnpaid();
	}
	
	@CrossOrigin("http://localhost:3000")
	@PutMapping("/markaspaid/{cartId}")
	public String markItemsAsPaid(@PathVariable Long cartId) {
	    try {
	        List<CartItems> items = service.findByCartId(cartId);
	        for (CartItems item : items) {
	            service.update(item);
	        }
	        return "Success";
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "Failure";
	    }
	}



}
