package com.pizza.serviceimpl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.pizza.model.Admin;
import com.pizza.model.Staff;
import com.pizza.repo.AdminRepo;
import com.pizza.service.AdminService;

import java.util.List;

@Service
public class AdminSerImpl implements AdminService {

    AdminRepo adminRepo;
    JavaMailSender mailSender;

    public AdminSerImpl(AdminRepo adminRepo, JavaMailSender mailSender) {
        this.adminRepo = adminRepo;
        this.mailSender = mailSender;
    }

    @Override
    public void addAdmin(Admin admin) {
        adminRepo.addAdmin(admin);
    }

    @Override
    public Admin getAdminById(Long id) {
        return adminRepo.getAdminById(id);
    }

    @Override
    public Admin getAdminByUsername(String username) {
        return adminRepo.getAdminByUsername(username);
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepo.getAllAdmins();
    }

    @Override
    public void addStaff(Staff staff) {
        adminRepo.addStaff(staff);
    }

//    @Override
//    public void updateStaffPassword(Long id, String newPassword) {
//        adminRepo.updateStaffPassword(id, newPassword);
//    }

    @Override
    public Staff getStaffById(Long id) {
        return adminRepo.getStaffById(id);
    }

    @Override
    public void sendCredentialsMail(String to, String staffName, String orgemail, String role, String plainPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("PizzaMan Staff Account Credentials");

        message.setText("Dear " + staffName + ",\n\n" +
                "We are pleased to inform you that a new account has been created for you. Your designated position is " + role.toUpperCase() + ".\n\n" +
                "To access your account, please use the following credentials:\n" +
                "Organization Email: " + orgemail + "\n" +
                "Temporary Password: " + plainPassword + "\n" +
                "Welcome to PizzaMan! We look forward to your contributions and wish you success in your new role.\n\n" +
                "Best regards,\n" +
                "The PizzaMan Team");

        mailSender.send(message);
    }

}
