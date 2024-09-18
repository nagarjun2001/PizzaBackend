package com.pizza.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pizza.model.FeastPoints;
import com.pizza.service.FeastPointsService;

@RestController
@RequestMapping("/points")
@CrossOrigin("http://localhost:3000")
public class FeastPointsController {

	String s = "Success";
	String f = "Failure";
	
	FeastPointsService service;
	
	public FeastPointsController(FeastPointsService service) {
		super();
		this.service = service;
	}

	@PostMapping
	public String addFeastPoints(@RequestBody FeastPoints fp) {
		String msg = "";
		try {
			service.addPoints(fp);
			msg = s;
		}
		catch (Exception e) {
			msg = f;
		}
		return msg;
	}
	
	@PutMapping
	public String updatePoints(@RequestBody FeastPoints fp) {
		String msg = "";
		try {
			service.updPoints(fp);
			msg = s;
		}
		catch (Exception e) {
			msg = f;
		}
		return msg;
	}
	
	@GetMapping("/{id}")
	public FeastPoints getFeastPointsById(@RequestParam("id") Long id) {
		return service.findById(id);
	}
	
	@GetMapping("/customer/{custid}")
	public FeastPoints getFeastPointsByCustId(@RequestParam("custid") Long custid) {
		return service.findByCustId(custid);
	}

	@DeleteMapping("/{id}")
	public String deleteFeastPointsById(@PathVariable("id") Long id) {
		String msg = "";
		try {
			service.deletePointsByCustId(id);
			msg = s;
		}
		catch (Exception e) {
			msg = f;
		}
		return msg;
	}
}
