package com.pizza.controller;

import com.pizza.model.Staff;
import com.pizza.serviceimpl.StaffSerImpl;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staff")
@CrossOrigin("http://localhost:3000")
public class StaffController {
	String s = "Success";
	String f = "Failure";

	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
    StaffSerImpl service;

    
    public StaffController(StaffSerImpl service) {
		super();
		this.service = service;
	}

	@PostMapping("/login")
    public String login(@RequestBody Staff staff) {
        Staff staffold = service.getStaffByOrgEmail(staff.getOrgemail());
        if (staffold == null) {
            return "Staff not found";
        }

        boolean isMatching = encoder.matches(staff.getPassword(), staffold.getPassword());
        if(isMatching) {
        	if(!staffold.isPwdchanged()) {
        		return "Password Change required!";
        	}
        	return s;
        }
        else {
        	return f;
        }
    }

    @GetMapping("/{id}")
    public Staff getStaffById(@PathVariable("id") Long id) {
        return service.getStaffById(id);
    }

    @GetMapping("/username/{username}")
    public Staff getStaffByUsername(@PathVariable("username") String username) {
        return service.getStaffByUsername(username);
    }
    
    @GetMapping("/email/{email}")
    public Staff getStaffByEmail(@PathVariable("email") String email) {
        return service.getStaffByEmail(email);
    }
    
    @GetMapping("/orgemail/{orgemail}")
    public Staff getStaffByOrgemail(@PathVariable("orgemail") String orgemail) {
        return service.getStaffByOrgEmail(orgemail);
    }

    @GetMapping("/all")
    public List<Staff> getAllStaffs() {
        return service.getAllStaffs();
    }

    @PutMapping("/updatepassword")
    public String updateStaffPassword(@RequestBody Staff staff) {
        try {
            Staff staffOld = service.getStaffById(staff.getId());
            if (staffOld == null) {
                return "Staff not found";
            }

            String encodedPassword = encoder.encode(staff.getPassword());
            staffOld.setPassword(encodedPassword);
            staffOld.setPwdchanged(true); 
            
            service.updateStaff(staffOld);

            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failure";
        }
    }
    
    @GetMapping("/role/{role}")
    public List<Staff> getStaffListByRole(@PathVariable("role") String role) {
        return service.getStaffListByRole(role);
    }
}
