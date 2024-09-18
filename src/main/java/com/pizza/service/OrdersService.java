package com.pizza.service;

import java.util.List;

import com.pizza.model.Orders;

public interface OrdersService {
	public void addOrder(Orders ord);
	public Orders getOrderByCustomerId(Long custid);
	public List<Orders> getAllOrder();
	public Orders getOrderByOrderId(Long id);
	public List<Orders> findByAcceptedOrders();
	public List<Orders> findByRejectedOrders();
	public void updateOrder(Orders order);
	public List<Orders> getOrderByStaffId(Long id);
	public String assignStaffToOrder(Long orderId, Long staffId);
}
