package com.pizza.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pizza.model.Cart;
import com.pizza.model.CartItems;
import com.pizza.model.Food;
import com.pizza.repoimpl.CartItemsRepoImpl;
import com.pizza.repoimpl.CartRepoImpl;
import com.pizza.repoimpl.FoodRepoImpl;
import com.pizza.service.CartItemsService;

@Service
public class CartItemsSerImpl implements CartItemsService {
	
	CartItemsRepoImpl cartItemRepo;
	CartRepoImpl cartRepo;
	FoodRepoImpl foodrepo;

	public CartItemsSerImpl(CartItemsRepoImpl cartItemRepo, CartRepoImpl cartRepo, FoodRepoImpl foodrepo) {
		super();
		this.cartItemRepo = cartItemRepo;
		this.cartRepo = cartRepo;
		this.foodrepo = foodrepo;
	}

	@Override
	public void addItemToCart(Long cartId, Long foodId, Long quantity) {
		Cart cart = cartRepo.getCartById(cartId);
        Food food = foodrepo.getFoodById(foodId);

        if (cart == null || food == null) {
            throw new RuntimeException("Cart or Food not found");
        }

        CartItems cartItems = cartItemRepo.findByCartIdAndFoodId(cartId, foodId);

        if (cartItems != null) {
            cartItems.setQty(cartItems.getQty() + quantity);
            cartItems.setTotal(cartItems.getQty() * food.getFprice());
            
            cartItemRepo.save(cartItems);
        } else {
            CartItems newCartItems = new CartItems();
            newCartItems.setCart(cart);
            newCartItems.setFood(food);
            newCartItems.setQty(quantity);
            newCartItems.setTotal(quantity * food.getFprice());
            
            cartItemRepo.save(newCartItems);
        }
	}

	@Override
	public void updateItemQuantity(Long cartItemId, Long quantity) {
		CartItems cartItems = cartItemRepo.findByCartIdAndFoodId(cartItemId, null);
        if (cartItems == null) {
            throw new RuntimeException("CartItem not found");
        }

        Food food = cartItems.getFood();
        cartItems.setQty(quantity);
        cartItems.setTotal(quantity * food.getFprice());
        cartItemRepo.save(cartItems);
	}

	@Override
	public void removeItemFromCart(Long cartItemId) {
        cartItemRepo.delete(cartItemId);
	}

	@Override
	public List<CartItems> getCartItemsByCartId(Long cartId) {
        return cartItemRepo.findByCartId(cartId);
	}

	
}