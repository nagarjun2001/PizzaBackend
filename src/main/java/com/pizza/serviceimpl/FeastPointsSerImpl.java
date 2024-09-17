package com.pizza.serviceimpl;

import org.springframework.stereotype.Service;

import com.pizza.model.FeastPoints;
import com.pizza.repoimpl.FeastPointsRepoImpl;
import com.pizza.service.FeastPointsService;

@Service
public class FeastPointsSerImpl implements FeastPointsService{
	FeastPointsRepoImpl repo;

	public FeastPointsSerImpl(FeastPointsRepoImpl repo) {
		super();
		this.repo = repo;
	}

	@Override
	public void addPoints(FeastPoints fp) {
		repo.addPoints(fp);
	}

	@Override
	public void updPoints(FeastPoints fp) {
		repo.updPoints(fp);
	}

	@Override
	public void deletePointsByCustId(Long custid) {
		repo.deletePointsByCustId(custid);
	}

	@Override
	public FeastPoints findByCustId(Long custid) {
		return repo.findByCustId(custid);
	}

	@Override
	public FeastPoints findById(Long id) {
		return repo.findById(id);
	}

}
