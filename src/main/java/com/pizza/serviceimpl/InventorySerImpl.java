package com.pizza.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pizza.model.Inventory;
import com.pizza.repoimpl.InventoryRepoImpl;
import com.pizza.service.InventoryService;

@Service
public class InventorySerImpl implements InventoryService{
	
	InventoryRepoImpl repo;

	public InventorySerImpl(InventoryRepoImpl repo) {
		super();
		this.repo = repo;
	}

	@Override
	public void addStock(Inventory in) {
		repo.addStock(in);
	}

	@Override
	public void updateStock(Inventory in) {
		repo.updateStock(in);
	}

	@Override
	public void deleteStock(Long id) {
		repo.deleteStock(id);
	}

	@Override
	public Inventory getStockById(Long id) {
		return repo.getStockById(id);
	}

	@Override
	public List<Inventory> getStockList() {
		return repo.getStockList();
	}

	@Override
	public List<Inventory> getRefillEnabled() {
		return repo.getRefillEnabled();
	}

	@Override
	public List<Inventory> getRefillDisabled() {
		return repo.getRefillDisabled();
	}

}
