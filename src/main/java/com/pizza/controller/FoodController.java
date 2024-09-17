package com.pizza.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pizza.model.Food;
import com.pizza.serviceimpl.FoodSerImpl;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/food")
public class FoodController {

	String s = "Success";
	String f = "Failure";
	
	FoodSerImpl service;

	public FoodController(FoodSerImpl service) {
		super();
		this.service = service;
	}
	
	@PostMapping
	public String addFood(@RequestParam("fname") String fname,
			@RequestParam("fdesc") String fdesc,
			@RequestParam("fprice") double fprice,
			@RequestParam("ftype") String ftype,
			@RequestParam("fimg") MultipartFile fimg
			) {
		String msg = "";
		try {
			Food f = new Food();
			f.setFdesc(fdesc);
			f.setFimg(fimg.getBytes());
			f.setFname(fname);
			f.setFprice(fprice);
			f.setFtype(ftype);
			
			service.addFood(f);
			msg = s;
		}
		catch (Exception e) {
			msg = f;
		}
		return msg;
	}
	
	@GetMapping("/{id}")
	public Food getFoodById(@PathVariable("id") Long id) {
		return service.getFoodById(id);
	}
	
	@GetMapping("/all")
	public List<Food> getAllFood() {
		return service.getFoodList();
	}
	
	@DeleteMapping("/{id}")
	public String deleteFoodById(@PathVariable("id") Long id) {
		String msg = "";
		try {
			service.deleteFood(id);
			msg = s;
		}
		catch (Exception e) {
			msg = f;
		}
		return msg;
	}
	
	@PutMapping("/{id}")
    public String updateFood(@PathVariable("id") Long id,
                 @RequestParam(value = "fname", required = false) String fname,
                 @RequestParam(value = "fdesc", required = false) String fdesc,
                 @RequestParam(value = "fprice", required = false) Double fprice,
                 @RequestParam(value = "ftype", required = false) String ftype,
                 @RequestParam(value = "fimg", required = false) MultipartFile fimg) {
        String msg = "";
        try {
            Food food = service.getFoodById(id);
            
            food.setFname(fname);
            food.setFdesc(fdesc);
            food.setFprice(fprice);
            food.setFtype(ftype);
            if (fimg != null && !fimg.isEmpty()) {
	            food.setFimg(fimg.getBytes());
	        }
            
            service.updateFood(food);
            msg = s;
        } catch (Exception e) {
        	e.printStackTrace();
            msg = f;
        }
        return msg;
    }
	
}
