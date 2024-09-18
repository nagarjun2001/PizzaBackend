package com.pizza.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pizza.model.CartItemsHistory;
import com.pizza.model.Orders;
import com.pizza.service.OrdersService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/orders")
public class OrdersController {

	String s = "Success";
	String f = "Failure";
	
	OrdersService service;
	
	public OrdersController(OrdersService service) {
		super();
		this.service = service;
	}

	@PostMapping
	@CrossOrigin("http://localhost:3000")
	public String addOrders(@RequestBody Orders ord) {
		String msg = "";
		try {
            for (CartItemsHistory item : ord.getItems()) {
                item.setOrder(ord);
            }
			ord.setOrderdate(LocalDate.now());
			ord.setPayment(true);
			service.addOrder(ord);
			msg = s;
		}
		catch (Exception e) {
			msg = f;
		}
		return msg;
	}
	
	@GetMapping("/all")
	public List<Orders> getAllOrders(){
		return service.getAllOrder();
	}
	
	@GetMapping("/cust/{custid}")
	public Orders getOrderByCustId(@PathVariable("custid") Long custid) {
		return service.getOrderByCustomerId(custid);
	}
	
	@GetMapping("/{id}")
	public Orders getOrderById(@PathVariable("id") Long id) {
		return service.getOrderByOrderId(id);
	}
	
	@GetMapping("/accepted")
	@CrossOrigin("http://localhost:3000")
	public List<Orders> getAcceptedOrders() {
		return service.findByAcceptedOrders();
	}
	
	@GetMapping("/notaccepted")
	@CrossOrigin("http://localhost:3000")
	public List<Orders> getNotAccepted() {
		return service.findByRejectedOrders();
	}
	
	@PutMapping("/updateStatus/{id}")
	@CrossOrigin("http://localhost:3000")
    public ResponseEntity<String> updateOrderStatus(@PathVariable("id") Long id, @RequestBody Orders ord) {
        try {
            Orders existingOrder = service.getOrderByOrderId(id);
            if (existingOrder == null) {
                return ResponseEntity.status(404).body("Order not found");
            }

            existingOrder.setIssuestatus(ord.getIssuestatus());
            existingOrder.setDelstatus(ord.getDelstatus());

            service.updateOrder(existingOrder);
            return ResponseEntity.ok("Order status updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to update order status");
        }
    }
	
	@PostMapping("/assignStaff")
	@CrossOrigin("http://localhost:3000")
    public String assignStaffToOrder(@RequestParam Long orderId, @RequestParam Long staffId) {
        return service.assignStaffToOrder(orderId, staffId);
    }
	
	@GetMapping("/staff/{id}")
	@CrossOrigin("http://localhost:3000")
	public List<Orders> getAllOrdersOfStaff(@PathVariable("id") Long id){
		return service.getOrderByStaffId(id);
	}
}
