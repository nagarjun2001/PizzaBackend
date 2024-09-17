package com.pizza.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pizza.model.Orders;
import com.pizza.repoimpl.OrdersRepoImpl;
import com.pizza.service.OrdersService;

@Service
public class OrdersSerImpl implements OrdersService{
	
	OrdersRepoImpl repo;

	public OrdersSerImpl(OrdersRepoImpl repo) {
		super();
		this.repo = repo;
	}

	@Override
	public void addOrder(Orders ord) {
		repo.addOrder(ord);
	}

	@Override
	public Orders getOrderByCustomerId(Long custid) {
		return repo.getOrderByCustomerId(custid);
	}

	@Override
	public List<Orders> getAllOrder() {
		return repo.getAllOrder();
	}

	@Override
	public Orders getOrderByOrderId(Long id) {
		return repo.getOrderByOrderId(id);
	}

	@Override
	public List<Orders> findByAcceptedOrders() {
		return repo.findByAcceptedOrders();
	}

	@Override
	public List<Orders> findByRejectedOrders() {
		return repo.findByRejectedOrders();
	}

	@Override
	public void updateOrder(Orders order) {
		repo.updateOrder(order);
	}

	@Override
	public String assignStaffToOrder(Long orderId, Long staffId) {
		return repo.assignStaffToOrder(orderId, staffId);
	}

	@Override
	public List<Orders> getOrderByStaffId(Long id) {
		return repo.getOrderByStaffId(id);
	}

	
}
