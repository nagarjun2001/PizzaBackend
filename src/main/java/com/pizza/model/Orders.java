//package com.pizza.model;
//
//import java.util.List;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.OneToOne;
//
//@Entity
//public class Orders {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//	
//	@ManyToOne
//	@JoinColumn
//	private Customer customer;
//	
//	@OneToOne
//	@JoinColumn
//	private Cart cart;
//	
//	@OneToOne
//	private Payment payment;
//	
//	private boolean isAccepted;
//
//	public Orders() {
//		super();
//	}
//
//	public Orders(Long id, Customer customer, Cart cart, Payment payment, boolean isAccepted) {
//		super();
//		this.id = id;
//		this.customer = customer;
//		this.cart = cart;
//		this.payment = payment;
//		this.isAccepted = isAccepted;
//	}
//
//	@Override
//	public String toString() {
//		return "Orders [id=" + id + ", customer=" + customer + ", cart=" + cart + ", payment=" + payment
//				+ ", isAccepted=" + isAccepted + "]";
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public Customer getCustomer() {
//		return customer;
//	}
//
//	public void setCustomer(Customer customer) {
//		this.customer = customer;
//	}
//
//	public Cart getCart() {
//		return cart;
//	}
//
//	public void setCart(Cart cart) {
//		this.cart = cart;
//	}
//
//	public Payment getPayment() {
//		return payment;
//	}
//
//	public void setPayment(Payment payment) {
//		this.payment = payment;
//	}
//
//	public boolean isAccepted() {
//		return isAccepted;
//	}
//
//	public void setAccepted(boolean isAccepted) {
//		this.isAccepted = isAccepted;
//	}
//
//}

package com.pizza.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn
	@JsonBackReference
	private Customer customer;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "order_id")
	@JsonManagedReference
	private List<CartItemsHistory> items = new ArrayList<>();
	
	@ManyToOne
	private Staff staff;
	
	private double total; 
	private String deladdress;
	private boolean payment = true;
	private String paymentmethod;
	private boolean isAccepted;
	private LocalDate orderdate;
	private String delstatus;
	private String issuestatus;

	public Orders() {
		super();
	}

	public Orders(Long id, Customer customer, List<CartItemsHistory> items, Staff staff, double total,
			String deladdress, boolean payment, String paymentmethod, boolean isAccepted, LocalDate orderdate,
			String delstatus, String issuestatus) {
		super();
		this.id = id;
		this.customer = customer;
		this.items = items;
		this.staff = staff;
		this.total = total;
		this.deladdress = deladdress;
		this.payment = payment;
		this.paymentmethod = paymentmethod;
		this.isAccepted = isAccepted;
		this.orderdate = orderdate;
		this.delstatus = delstatus;
		this.issuestatus = issuestatus;
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", customer=" + customer + ", items=" + items + ", staff=" + staff + ", total="
				+ total + ", deladdress=" + deladdress + ", payment=" + payment + ", paymentmethod=" + paymentmethod
				+ ", isAccepted=" + isAccepted + ", orderdate=" + orderdate + ", delstatus=" + delstatus
				+ ", issuestatus=" + issuestatus + "]";
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

	public List<CartItemsHistory> getItems() {
		return items;
	}

	public void setItems(List<CartItemsHistory> items) {
		this.items = items;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getDeladdress() {
		return deladdress;
	}

	public void setDeladdress(String deladdress) {
		this.deladdress = deladdress;
	}

	public boolean isPayment() {
		return payment;
	}

	public void setPayment(boolean payment) {
		this.payment = payment;
	}

	public String getPaymentmethod() {
		return paymentmethod;
	}

	public void setPaymentmethod(String paymentmethod) {
		this.paymentmethod = paymentmethod;
	}

	public boolean isAccepted() {
		return isAccepted;
	}

	public void setAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	public LocalDate getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(LocalDate orderdate) {
		this.orderdate = orderdate;
	}

	public String getDelstatus() {
		return delstatus;
	}

	public void setDelstatus(String delstatus) {
		this.delstatus = delstatus;
	}

	public String getIssuestatus() {
		return issuestatus;
	}

	public void setIssuestatus(String issuestatus) {
		this.issuestatus = issuestatus;
	}

}
