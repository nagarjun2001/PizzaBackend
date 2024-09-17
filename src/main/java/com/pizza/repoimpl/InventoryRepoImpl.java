package com.pizza.repoimpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pizza.model.Inventory;
import com.pizza.repo.InventoryRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class InventoryRepoImpl implements InventoryRepo{

	EntityManager emanager;

	public InventoryRepoImpl(EntityManager emanager) {
		super();
		this.emanager = emanager;
	}

	@Override
	public void addStock(Inventory in) {
		emanager.persist(in);
	}

	@Override
	public void updateStock(Inventory in) {
		emanager.merge(in);
	}

	@Override
	public void deleteStock(Long id) {
		Inventory delobj = getStockById(id);
		emanager.remove(delobj);
	}

	@Override
	public Inventory getStockById(Long id) {
		return emanager.find(Inventory.class, id);
	}

	@Override
	public List<Inventory> getStockList() {
		String hql = "From Inventory";
		Query q = emanager.createQuery(hql);
		return q.getResultList();
	}

	@Override
	public List<Inventory> getRefillEnabled() {
		String hql = "From Inventory i where i.isRefillSelected = :isRefillSelected";
		Query q = emanager.createQuery(hql);
		q.setParameter("isRefillSelected", "true");
		return q.getResultList();
	}

	@Override
	public List<Inventory> getRefillDisabled() {
		String hql = "From Inventory i where i.isRefillSelected = :isRefillSelected";
		Query q = emanager.createQuery(hql);
		q.setParameter("isRefillSelected", "false");
		return q.getResultList();
	}
	
}
