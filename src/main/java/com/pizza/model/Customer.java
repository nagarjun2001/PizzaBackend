package com.pizza.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String fname;
	private String lname;
	private String uname;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	private Long mobno;
	private String address;
	
	private String otp;
	private LocalDateTime otpexpiry;
	
	@OneToMany(mappedBy = "customer")
    @JsonManagedReference
    private List<Orders> orders = new ArrayList<>();
	
	public Customer() {
		super();
	}

	public Customer(Long id, String fname, String lname, String uname, String email, String password, Long mobno,
			String address, String otp, LocalDateTime otpexpiry, List<Orders> orders) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.uname = uname;
		this.email = email;
		this.password = password;
		this.mobno = mobno;
		this.address = address;
		this.otp = otp;
		this.otpexpiry = otpexpiry;
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", fname=" + fname + ", lname=" + lname + ", uname=" + uname + ", email=" + email
				+ ", password=" + password + ", mobno=" + mobno + ", address=" + address + ", otp=" + otp
				+ ", otpexpiry=" + otpexpiry + ", orders=" + orders + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getMobno() {
		return mobno;
	}

	public void setMobno(Long mobno) {
		this.mobno = mobno;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public LocalDateTime getOtpexpiry() {
		return otpexpiry;
	}

	public void setOtpexpiry(LocalDateTime otpexpiry) {
		this.otpexpiry = otpexpiry;
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

}
