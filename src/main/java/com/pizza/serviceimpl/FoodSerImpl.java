package com.pizza.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pizza.model.Food;
import com.pizza.repoimpl.FoodRepoImpl;
import com.pizza.service.FoodService;

@Service
public class FoodSerImpl implements FoodService{

	FoodRepoImpl repo;

	public FoodSerImpl(FoodRepoImpl repo) {
		super();
		this.repo = repo;
	}

	@Override
	public void addFood(Food food) {
		repo.addFood(food);
	}

	@Override
	public void updateFood(Food food) {
		repo.updateFood(food);
	}

	@Override
	public void deleteFood(Long id) {
		repo.deleteFood(id);
	}

	@Override
	public Food getFoodById(Long id) {
		return repo.getFoodById(id);
	}

	@Override
	public List<Food> getFoodList() {
		return repo.getFoodList();
	}
	
}
