package com.pizza.repo;

import com.pizza.model.FeastPoints;

public interface FeastPointsRepo {
	public void addPoints(FeastPoints fp);
	public void updPoints(FeastPoints fp);
	public void deletePointsByCustId(Long custid);
	public FeastPoints findByCustId(Long custid);
	public FeastPoints findById(Long id);
}
