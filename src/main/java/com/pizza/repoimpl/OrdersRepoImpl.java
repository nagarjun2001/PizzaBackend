package com.pizza.repoimpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pizza.model.Orders;
import com.pizza.model.Staff;
import com.pizza.repo.OrdersRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class OrdersRepoImpl implements OrdersRepo{
	
	EntityManager emanager;

	public OrdersRepoImpl(EntityManager emanager) {
		super();
		this.emanager = emanager;
	}

	@Override
	public void addOrder(Orders ord) {
		emanager.persist(ord);
	}

	@Override
	public Orders getOrderByCustomerId(Long custid) {
		String hql = "From Orders o where o.customer.id = :custid";
		Query q = emanager.createQuery(hql);
		q.setParameter("custid", custid);
		List<Orders> results = q.getResultList();
	    return results.isEmpty() ? null : results.get(0);
	}

	@Override
	public List<Orders> getAllOrder() {
		String hql = "From Orders";
		Query q = emanager.createQuery(hql);
		return q.getResultList();
	}

	@Override
	public Orders getOrderByOrderId(Long id) {
		String hql = "From Orders o where o.id = :id";
		Query q = emanager.createQuery(hql);
		q.setParameter("id", id);
		return (Orders) q.getSingleResult();
	}

	@Override
	public List<Orders> findByAcceptedOrders() {
		String hql = "From Orders o where isAccepted = true";
		Query q = emanager.createQuery(hql);
		return q.getResultList();
	}

	@Override
	public List<Orders> findByRejectedOrders() {
		String hql = "From Orders o where isAccepted = false";
		Query q = emanager.createQuery(hql);
		return q.getResultList();
	}

	@Override
	public void updateOrder(Orders order) {
		emanager.merge(order);
	}
	
	@Override
	public String assignStaffToOrder(Long orderId, Long staffId) {
		Orders order = getOrderByOrderId(orderId);
        if (order == null) {
            return "Order not found";
        }
        order.setAccepted(true);
        Staff staff = emanager.find(Staff.class, staffId);
        if (staff == null) {
            return "Staff not found";
        }
        Query query = emanager.createNativeQuery("UPDATE orders SET staff_id = :staffId, is_accepted = :isAccepted WHERE id = :orderId");
        query.setParameter("staffId", staffId);
        query.setParameter("isAccepted", true);
        query.setParameter("orderId", orderId);

        int updatedRows = query.executeUpdate();

        if (updatedRows > 0) {
            return "Staff assigned successfully";
        } else {
            return "Failed to assign staff";
        }
    }

	@Override
	public List<Orders> getOrderByStaffId(Long id) {
		String hql = "From Orders o where o.staff.id = :id";
		Query q = emanager.createQuery(hql);
		q.setParameter("id", id);
		return q.getResultList();
	}

}
