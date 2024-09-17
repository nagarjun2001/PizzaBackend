package com.pizza.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class FeastPoints {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long earned;
	private Long balance;
	
	@OneToOne
	@JoinColumn
	private Orders orders;

	public FeastPoints() {
		super();
	}

	public FeastPoints(Long id, Long earned, Long balance, Orders orders) {
		super();
		this.id = id;
		this.earned = earned;
		this.balance = balance;
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "FeastPoints [id=" + id + ", earned=" + earned + ", balance=" + balance + ", orders=" + orders + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEarned() {
		return earned;
	}

	public void setEarned(Long earned) {
		this.earned = earned;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

}
