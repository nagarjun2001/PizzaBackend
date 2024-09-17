package com.pizza.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "custid", unique = true)
	private Customer customer;	
	
	private Long total;
	
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CartItems> cartiem = new ArrayList<>();

	public Cart() {
		super();
	}

	public Cart(Long id, Customer customer, Long total, List<CartItems> cartiem) {
		super();
		this.id = id;
		this.customer = customer;
		this.total = total;
		this.cartiem = cartiem;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", customer=" + customer + ", total=" + total + ", cartiem=" + cartiem + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<CartItems> getCartiem() {
		return cartiem;
	}

	public void setCartiem(List<CartItems> cartiem) {
		this.cartiem = cartiem;
	}

}
