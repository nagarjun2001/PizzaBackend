package com.pizza.repoimpl;

import org.springframework.stereotype.Repository;

import com.pizza.model.FeastPoints;
import com.pizza.repo.FeastPointsRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class FeastPointsRepoImpl implements FeastPointsRepo{

	EntityManager emanager;

	public FeastPointsRepoImpl(EntityManager emanager) {
		super();
		this.emanager = emanager;
	}

	@Override
	public void addPoints(FeastPoints fp) {
		emanager.persist(fp);
	}

	@Override
	public void updPoints(FeastPoints fp) {
		emanager.merge(fp);
	}

	@Override
	public FeastPoints findByCustId(Long id) {
		String hql = "From FeastPoints fp where fp.orders.customer.id = :id";
		Query q = emanager.createQuery(hql);
		q.setParameter("id", id);
		return (FeastPoints) q.getSingleResult();
	}

	@Override
	public FeastPoints findById(Long id) {
		return emanager.find(FeastPoints.class, id);
	}

	@Override
	public void deletePointsByCustId(Long custid) {
		FeastPoints delobj = findByCustId(custid);
		emanager.remove(delobj);
	}
	
}
