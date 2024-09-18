package com.pizza.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CartItemsHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long cartId;
    private Long foodId;
    private double total;
    private Long qty;
    private boolean paid;
    private Long customerId;
    
    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Orders order;
    
    public CartItemsHistory() {
		super();
	}

	public CartItemsHistory(Long id, Long cartId, Long foodId, double total, Long qty, boolean paid, Long customerId,
			Orders order) {
		super();
		this.id = id;
		this.cartId = cartId;
		this.foodId = foodId;
		this.total = total;
		this.qty = qty;
		this.paid = paid;
		this.customerId = customerId;
		this.order = order;
	}

	@Override
	public String toString() {
		return "CartItemsHistory [id=" + id + ", cartId=" + cartId + ", foodId=" + foodId + ", total=" + total
				+ ", qty=" + qty + ", paid=" + paid + ", customerId=" + customerId + ", order=" + order + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public Long getFoodId() {
		return foodId;
	}

	public void setFoodId(Long foodId) {
		this.foodId = foodId;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Long getQty() {
		return qty;
	}

	public void setQty(Long qty) {
		this.qty = qty;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

}
