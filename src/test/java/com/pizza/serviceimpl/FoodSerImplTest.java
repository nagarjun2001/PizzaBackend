package com.pizza.serviceimpl;

import com.pizza.model.Food;
import com.pizza.repoimpl.FoodRepoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class FoodSerImplTest {

    @Mock
    private FoodRepoImpl repo;

    @InjectMocks
    private FoodSerImpl foodSerImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddFood() {
        Food food = new Food();
        foodSerImpl.addFood(food);
        verify(repo, times(1)).addFood(food);
    }

    @Test
    void testUpdateFood() {
        Food food = new Food();
        foodSerImpl.updateFood(food);
        verify(repo, times(1)).updateFood(food);
    }

    @Test
    void testDeleteFood() {
        Long id = 1L;
        foodSerImpl.deleteFood(id);
        verify(repo, times(1)).deleteFood(id);
    }

    @Test
    void testGetFoodList() {
        List<Food> foods = Collections.singletonList(new Food());
        when(repo.getFoodList()).thenReturn(foods);

        List<Food> result = foodSerImpl.getFoodList();
        verify(repo, times(1)).getFoodList();
        assertEquals(foods, result);
    }
}
