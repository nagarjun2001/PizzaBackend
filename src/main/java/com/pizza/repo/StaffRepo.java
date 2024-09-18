package com.pizza.repo;

import com.pizza.model.Staff;

import java.util.List;

public interface StaffRepo {
	public Staff getStaffById(Long id);
	public Staff getStaffByUsername(String username);
	public Staff getStaffByEmail(String email);
	public List<Staff> getStaffListByRole(String role);
    Staff getStaffByOrgEmail(String orgemail);
	public List<Staff> getAllStaffs();
	public void updateStaffPassword(Long id, String newPassword);
	public Staff updateStaff(Staff staff);
}
