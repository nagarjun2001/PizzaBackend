package com.pizza.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pizza.model.Inventory;
import com.pizza.serviceimpl.InventorySerImpl;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/stock")
public class InventoryController {
	
	InventorySerImpl service;

	public InventoryController(InventorySerImpl service) {
		super();
		this.service = service;
	}
	
	String s = "Success";
	String f = "Failure";
	
	@PostMapping
	public String addStockDetails(@RequestParam("itemname") String itemname,
			@RequestParam("itemqty") Long itemqty,
			@RequestParam("lowlevel") Long lowlevel,
			@RequestParam("refillLevel") Long refillLevel,
			@RequestParam("isRefillSelected") String isRefillSelected,
			@RequestPart("itemimg") MultipartFile itemimg
			) {
		String msg = "";
		try {
			Inventory i = new Inventory();
			i.setItemimg(itemimg.getBytes());
			i.setItemname(itemname);
			i.setItemqty(itemqty);
			i.setLowlevel(lowlevel);
			i.setIsRefillSelected(isRefillSelected);
			i.setRefillLevel(refillLevel);
			i.setItemimg(itemimg.getBytes());
			
			service.addStock(i);
			msg = s;
		}
		catch (Exception e) {
			msg = f;
		}
		return msg;
	}
	
	@PutMapping("/{id}")
	public String updateStock(@PathVariable("id") Long id,
	                          @RequestParam("itemname") String itemname,
	                          @RequestParam("itemqty") Long itemqty,
	                          @RequestParam("lowlevel") Long lowlevel,
	                          @RequestParam("refillLevel") Long refillLevel,
	                          @RequestParam("isRefillSelected") String isRefillSelected,
	                          @RequestPart(value = "itemimg", required = false) MultipartFile itemimg) {
	    String msg;
	    try {
	        Inventory i = service.getStockById(id);
	        if (i != null) {
	            i.setItemname(itemname);
	            i.setItemqty(itemqty);
	            i.setLowlevel(lowlevel);
	            i.setRefillLevel(refillLevel);
                i.setIsRefillSelected(isRefillSelected);
                if (itemimg != null && !itemimg.isEmpty()) {
                    i.setItemimg(itemimg.getBytes());
                }

                if (i.getItemqty() <= i.getLowlevel() && "true".equalsIgnoreCase(i.getIsRefillSelected())) {
                    i.setItemqty(i.getRefillLevel());
                }

	            service.updateStock(i);
	            msg = "Success";
	        } else {
	            msg = "Failure: Item not found";
	        }
	    } catch (Exception e) {
	        msg = "Failure";
	    }
	    return msg;
	}

	
	@PutMapping("/takeout/{id}")
	public ResponseEntity<String> takeOutStock(@PathVariable("id") Long id,
	                                           @RequestBody TakeOutRequest takeOutRequest) {
	    try {
	        if (takeOutRequest == null || takeOutRequest.getTakeOutQuantity() == null) {
	            return ResponseEntity.badRequest().body("Failure: Take out quantity must be provided");
	        }

	        Long takeOutQuantity = takeOutRequest.getTakeOutQuantity();

	        if (takeOutQuantity <= 0) {
	            return ResponseEntity.badRequest().body("Failure: Quantity to take out must be greater than 0");
	        }

	        Inventory inventory = service.getStockById(id);
	        if (inventory != null) {
	            Long currentQuantity = inventory.getItemqty();
	            
	            if (currentQuantity == null) {
	                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failure: Current quantity is null");
	            }

	            Long newQuantity = currentQuantity - takeOutQuantity;

	            if (newQuantity < 0) {
	                return ResponseEntity.badRequest().body("Failure: Quantity cannot be negative");
	            }

	            if (newQuantity <= inventory.getLowlevel() && "true".equalsIgnoreCase(inventory.getIsRefillSelected())) {
	                newQuantity = inventory.getRefillLevel();
	            }

	            inventory.setItemqty(newQuantity);
	            service.updateStock(inventory);
	            return ResponseEntity.ok("Success");
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failure: Item not found");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failure: An error occurred");
	    }
	}



	
	@DeleteMapping("/{id}")
	public String deleteStock(@PathVariable("id") Long id) {
		String msg = "";
		try {
			service.deleteStock(id);
			msg = s;
		}
		catch (Exception e) {
			msg = f;
		}
		return msg;
	}
	
	@GetMapping("/{id}")
	public Inventory getStockById(@PathVariable("id") Long id) {
		return service.getStockById(id);
	}
	
	@GetMapping("/all")
	public List<Inventory> getStockList() {
		return service.getStockList();
	}
	
	@GetMapping("/refillenabled")
	public List<Inventory> getRefillEnabledStock(){
		return service.getRefillEnabled();
	}
	
	@GetMapping("/refilldisabled")
	public List<Inventory> getRefillDisabledStock(){
		return service.getRefillDisabled();
	}
	
	public static class TakeOutRequest {
        private Long takeOutQuantity;

        public TakeOutRequest() {}

        public Long getTakeOutQuantity() {
            return takeOutQuantity;
        }

        public void setTakeOutQuantity(Long takeOutQuantity) {
            this.takeOutQuantity = takeOutQuantity;
        }
    }
}


