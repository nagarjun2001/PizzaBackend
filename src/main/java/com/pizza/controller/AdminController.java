package com.pizza.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pizza.model.Admin;
import com.pizza.model.Staff;
import com.pizza.service.AdminService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
@CrossOrigin("http://localhost:3000")
public class AdminController {
    String s = "Success";
    String f = "Failure";

    AdminService service;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public AdminController(AdminService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public String addAdmin(@RequestBody Admin admin) {
        try {
            admin.setPassword(encoder.encode(admin.getPassword()));
            service.addAdmin(admin);
            return s;
        } catch (Exception e) {
            return f;
        }
    }

    @PostMapping("/login")
    public String login(@RequestBody Admin admin) {
        Admin adminold = service.getAdminByUsername(admin.getUsername());
        if (adminold == null) {
            return "Admin not found";
        }

        boolean isMatching = encoder.matches(admin.getPassword(), adminold.getPassword());
        return isMatching ? s : f;
    }

    @GetMapping("/{id}")
    public Admin getAdminById(@PathVariable("id") Long id) {
        return service.getAdminById(id);
    }

    @GetMapping("/username/{username}")
    public Admin getAdminByUsername(@PathVariable("username") String username) {
        return service.getAdminByUsername(username);
    }

    @GetMapping("/all")
    public List<Admin> getAllAdmins() {
        return service.getAllAdmins();
    }

    @CrossOrigin("http://localhost:3000")
    @PostMapping("/addstaff")
    public String addStaff(@RequestBody Staff staff) {
        try {
            String orgemail = generateEmailFromFullName(staff.getStaffName());
        	String textpwd = generateRandomPassword();
            String encpwd = encoder.encode(textpwd);
            staff.setOrgemail(orgemail);
            staff.setPassword(encpwd);
            service.addStaff(staff);
            service.sendCredentialsMail(staff.getEmail(),orgemail, staff.getStaffName(), staff.getRole(), textpwd);
            return s;
        } catch (Exception e) {
            return f;
        }
    }

    private String generateEmailFromFullName(String staffName) {
    	String username = staffName.replaceAll("\\s", "").toLowerCase();
        return username + "@pizzaman.com";
    }

	private String generateRandomPassword() {
        return UUID.randomUUID().toString().substring(0, 12);
    }
}
