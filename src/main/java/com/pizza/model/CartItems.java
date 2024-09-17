package com.pizza.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CartItems {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private double total;
	private Long qty;
	
	@ManyToOne
	@JoinColumn(name = "foodid")
	private Food food;
		
	@ManyToOne
    @JoinColumn(name = "cartid")
	@JsonBackReference
    private Cart cart;

	public CartItems() {
		super();
	}

	public CartItems(Long id, double total, Long qty, Food food, Cart cart) {
		super();
		this.id = id;
		this.total = total;
		this.qty = qty;
		this.food = food;
		this.cart = cart;
	}

	@Override
	public String toString() {
		return "CartItems [id=" + id + ", total=" + total + ", qty=" + qty + ", food=" + food + ", cart=" + cart + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

}
