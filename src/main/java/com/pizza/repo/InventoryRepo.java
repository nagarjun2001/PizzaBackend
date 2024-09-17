package com.pizza.repo;

import java.util.List;

import com.pizza.model.Inventory;

public interface InventoryRepo {
	public void addStock(Inventory in);
	public void updateStock(Inventory in);
	public void deleteStock(Long id);
	public Inventory getStockById(Long id);
	public List<Inventory> getStockList();
	public List<Inventory> getRefillEnabled();
	public List<Inventory> getRefillDisabled();
}
