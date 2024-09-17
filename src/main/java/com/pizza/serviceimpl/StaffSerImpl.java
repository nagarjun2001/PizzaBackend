package com.pizza.serviceimpl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pizza.model.Staff;
import com.pizza.repo.StaffRepo;
import com.pizza.service.StaffService;

import java.util.List;

@Service
public class StaffSerImpl implements StaffService {

    StaffRepo repo;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public StaffSerImpl(StaffRepo repo) {
        this.repo = repo;
    }

    @Override
    public Staff getStaffById(Long id) {
        return repo.getStaffById(id);
    }

    @Override
    public Staff getStaffByUsername(String username) {
        return repo.getStaffByUsername(username);
    }
    
    @Override
    public Staff getStaffByEmail(String email) {
        return repo.getStaffByEmail(email);
    }

    @Override
    public List<Staff> getAllStaffs() {
        return repo.getAllStaffs();
    }

    @Override
    public void updateStaffPassword(Long id, String newPassword) {
        String encpwd = encoder.encode(newPassword);
        repo.updateStaffPassword(id, encpwd);
    }

	@Override
	public Staff getStaffByOrgEmail(String orgemail) {
		return repo.getStaffByOrgEmail(orgemail);
	}

	@Override
	public Staff updateStaff(Staff staff) {
		return repo.updateStaff(staff);
	}

	@Override
	public List<Staff> getStaffListByRole(String role) {
		return repo.getStaffListByRole(role);
	}
}
