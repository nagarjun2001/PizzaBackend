package com.pizza.service;

import java.util.List;

import com.pizza.model.Food;

public interface FoodService {
	public void addFood(Food food);
	public void updateFood(Food food);
	public void deleteFood(Long id);
	public Food getFoodById(Long id);
	public List<Food> getFoodList();
}
