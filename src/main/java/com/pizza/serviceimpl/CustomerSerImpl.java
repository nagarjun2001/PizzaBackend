package com.pizza.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pizza.model.Customer;
import com.pizza.repoimpl.CustomerRepoImpl;
import com.pizza.service.CustomerService;

@Service
public class CustomerSerImpl implements CustomerService{
	
	CustomerRepoImpl repo;

	public CustomerSerImpl(CustomerRepoImpl repo) {
		super();
		this.repo = repo;
	}

	@Override
	public void saveCustomer(Customer cust) {
		repo.saveCustomer(cust);
	}

	@Override
	public Customer getCustById(Long id) {
		return repo.getCustById(id);
	}

	@Override
	public List<Customer> getAllCustomer() {
		return repo.getAllCustomer();
	}

	@Override
	public void updateCustomer(Customer cust) {
		repo.updateCustomer(cust);
	}

	@Override
	public void deleteCustomer(Long id) {
		repo.deleteCustomer(id);
	}

	@Override
	public Customer findByEmail(String email) {
		return repo.findByEmail(email);
	}

	@Override
	public Customer findByOtp(String otp) {
		return repo.findByOtp(otp);
	}

}
