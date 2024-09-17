package com.pizza.service;

import com.pizza.model.FeastPoints;

public interface FeastPointsService {
	public void addPoints(FeastPoints fp);
	public void updPoints(FeastPoints fp);
	public void deletePointsByCustId(Long custid);
	public FeastPoints findByCustId(Long custid);
	public FeastPoints findById(Long id);
}
