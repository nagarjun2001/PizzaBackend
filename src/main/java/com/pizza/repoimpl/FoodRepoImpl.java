package com.pizza.repoimpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pizza.model.Food;
import com.pizza.repo.FoodRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class FoodRepoImpl implements FoodRepo{

	EntityManager emanager;

	public FoodRepoImpl(EntityManager emanager) {
		super();
		this.emanager = emanager;
	}

	@Override
	public void addFood(Food food) {
		emanager.persist(food);
	}

	@Override
	public void updateFood(Food food) {
		emanager.merge(food);
	}

	@Override
	public void deleteFood(Long id) {
		Food foodobj = getFoodById(id);
		emanager.remove(foodobj);
	}

	@Override
	public Food getFoodById(Long id) {
		return emanager.find(Food.class, id);
	}

	@Override
	public List<Food> getFoodList() {
		String hql = "From Food";
		Query q = emanager.createQuery(hql);
		return q.getResultList();
	}
	
}
