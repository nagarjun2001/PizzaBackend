package com.pizza.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pizza.model.Customer;
import com.pizza.serviceimpl.CustomerSerImpl;

@RestController
@RequestMapping("/customer")
@CrossOrigin("http://localhost:3000")
public class CustomerController {

	String s = "Success";
	String f = "Failure";
	
	CustomerSerImpl service;
	JavaMailSender mail;

	public CustomerController(CustomerSerImpl service, JavaMailSender mail) {
		super();
		this.service = service;
		this.mail = mail;
	}

	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(8);
	
	@PostMapping
	public String addCustomer(@RequestBody Customer cust) {
		String msg = "";
		try {
			Customer existingCustomer = service.findByEmail(cust.getEmail());
	        if (existingCustomer != null) {
	            return "Email already exists.";
	        }
	        
	        if(existingCustomer == null) {
				String otp = generateOtp();
				sendOtp(cust.getEmail(), otp, cust.getFname());
				
				cust.setOtp(otp);
				cust.setOtpexpiry(LocalDateTime.now().plusMinutes(5));
				cust.setPassword(null);
				
				service.saveCustomer(cust);
				msg = s;
	        }
		}
		catch (Exception e) {
			e.printStackTrace();
			msg = f;
		}
		return msg;
	}
	
	@GetMapping("/{id}")
	public Customer getCustById(@PathVariable("id") Long id) {
		return service.getCustById(id);
	}
	
	@GetMapping("/all")
	public List<Customer> getAllCustmer() {
		return service.getAllCustomer();
	}
	
//	@PostMapping("/verifyotp")
//	public String verifyOTP(@RequestParam(value = "email") String email, @RequestParam(value = "otp") String otp) {
//		System.out.println("Received email: " + email);
//	    System.out.println("Received OTP: " + otp);
//	    Customer customer = service.findByEmail(email);
//
//	    if (customer.getOtp() == null || !customer.getOtp().equals(otp) || customer.getOtpexpiry().isBefore(LocalDateTime.now())) {
//	        return "Invalid or expired OTP!";
//	    }
//
//	    customer.setOtp(null);
//	    customer.setOtpexpiry(null);
//	    service.updateCustomer(customer);
//
//	    return "OTP Verified";
//	}
//	
//	@PostMapping("/updatepwd")
//    public String updatePassword(@RequestParam String email, @RequestParam String pwd) {
//        Customer customer = service.findByEmail(email);
//        if (customer == null) {
//            return "Customer not found!";
//        }
//
//        String encpwd = encoder.encode(pwd);
//        customer.setPassword(encpwd);
//        service.updateCustomer(customer);
//
//        return "Password updated successfully!";
//    }
	
	@PostMapping("/verifyotp")
    public String verifyOtp(@RequestBody Customer verify) {

        Customer customer = service.findByEmail(verify.getEmail());

        if (customer == null) {
            return "Customer not found!";
        }

        if (customer.getOtp() == null || !customer.getOtp().equals(verify.getOtp()) || customer.getOtpexpiry().isBefore(LocalDateTime.now())) {
            return "Invalid or expired OTP!";
        }

        customer.setOtp(null);
        customer.setOtpexpiry(null);
        service.updateCustomer(customer);

        return "OTP Verified";
    }
    
    @PostMapping("/updatepwd")
    public String updatePassword(@RequestBody Customer upd) {
        Customer customer = service.findByEmail(upd.getEmail());
        if (customer == null) {
            return "Customer not found!";
        }

        String encpwd = encoder.encode(upd.getPassword());
        customer.setPassword(encpwd);
        service.updateCustomer(customer);

        return "Password updated successfully!";
    }
    
//	@PostMapping("/login")
//	public String loginCust(@RequestBody Customer cust) {
//			Customer custold = service.findByEmail(cust.getEmail());
//			if(custold == null) {
//				return "Customer not found!";
//			}
//			
//			boolean isMatching = encoder.matches(cust.getPassword(), custold.getPassword());
//			return isMatching ? s: f;
//		}

    @PostMapping("/login")
    public String loginCust(@RequestBody Customer cust) {
        try {
            Customer custold = service.findByEmail(cust.getEmail());
            if (custold == null) {
                return "Customer not found!";
            }
            
            boolean isMatching = encoder.matches(cust.getPassword(), custold.getPassword());
            if (isMatching) {
                return "Success:" + custold.getId();
            } else {
                return "Failure";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Failure";
        }
    }
	
	//Cheezza
	private void sendOtp(String email, String otp, String fname) {
		SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Pizzaman Registration OTP");
        message.setText(
                "Dear "+fname+",\n\n" +
                "Thank you for registering with Pizzaman.\n\n" +
                "As part of the registration process, please use the following One-Time Password (OTP) to verify your email address:\n\n" +
                "OTP Code: " + otp + "\n\n" +
                "For security purposes, this code is valid for 5 minutes only. If you did not requested this OTP or believe this message was sent in error, please ignore this email.\n\n" +
                "Best regards,\n" +
                "The Pizzaman Team"
            );
        mail.send(message);
	}

	private String generateOtp() {
		Random random = new Random();
		int otp = 100000 + random.nextInt(900000);
		return String.valueOf(otp);
	}
}
