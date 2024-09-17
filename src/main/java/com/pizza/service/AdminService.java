package com.pizza.service;

import com.pizza.model.Admin;
import com.pizza.model.Staff;

import java.util.List;

public interface AdminService {

    void addAdmin(Admin admin);
    Admin getAdminById(Long id);
    Admin getAdminByUsername(String username);
    List<Admin> getAllAdmins();

    void addStaff(Staff staff);
//    void updateStaffPassword(Long id, String newPassword);
    Staff getStaffById(Long id);
    void sendCredentialsMail(String to, String staffName, String orgemail, String role, String plainPassword);
}
