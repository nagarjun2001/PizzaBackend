package com.pizza.repo;

import java.util.List;

import com.pizza.model.Customer;

public interface CustomerRepo {

	public void saveCustomer(Customer cust);
	public Customer getCustById(Long id);
	public List<Customer> getAllCustomer();
	public void updateCustomer(Customer cust);
	public void deleteCustomer(Long id);
	public Customer findByEmail(String email);
	public Customer findByOtp(String otp);
}
