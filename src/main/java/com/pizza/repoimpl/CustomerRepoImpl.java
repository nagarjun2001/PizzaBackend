package com.pizza.repoimpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pizza.model.Customer;
import com.pizza.repo.CustomerRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class CustomerRepoImpl implements CustomerRepo{

	EntityManager emanager;

	public CustomerRepoImpl(EntityManager emanager) {
		super();
		this.emanager = emanager;
	}

	@Override
	public void saveCustomer(Customer cust) {
		emanager.persist(cust);
	}

	@Override
	public Customer getCustById(Long id) {
		return emanager.find(Customer.class, id);
	}

	@Override
	public List<Customer> getAllCustomer() {
		String hql = "From Customer";
		Query q = emanager.createQuery(hql);
		return q.getResultList();
	}

	@Override
	public void updateCustomer(Customer cust) {
		emanager.merge(cust);
	}

	@Override
	public void deleteCustomer(Long id) {
		Customer delcust = getCustById(id);
		emanager.remove(delcust);
	}

	@Override
	public Customer findByEmail(String email) {
		String hql = "From Customer c where c.email = :email";
		Query q = emanager.createQuery(hql);
		q.setParameter("email", email);
		try {
	        return (Customer) q.getSingleResult();
	    } catch (NoResultException e) {
	        return null;
	    }
	}

	@Override
	public Customer findByOtp(String otp) {
		String hql = "FROM Customer c WHERE c.otp = :otp";
        Query q = emanager.createQuery(hql);
        q.setParameter("otp", otp);
        return (Customer) q.getSingleResult();
	}
	
}
