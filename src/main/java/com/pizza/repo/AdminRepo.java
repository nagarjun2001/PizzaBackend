package com.pizza.repo;

import com.pizza.model.Admin;
import com.pizza.model.Staff;

import java.util.List;

public interface AdminRepo {

    public void addAdmin(Admin admin);
    public Admin getAdminById(Long id);
    public Admin getAdminByUsername(String username);
    public List<Admin> getAllAdmins();
    
    public void addStaff(Staff staff);
//    public void updateStaffPassword(Long id, String newPassword);
    public Staff getStaffById(Long id);
    public void deleteStaffById(Long id);
}
