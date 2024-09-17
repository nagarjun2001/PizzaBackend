package com.pizza.repoimpl;

import org.springframework.stereotype.Repository;

import com.pizza.model.Staff;
import com.pizza.repo.StaffRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.util.List;

@Repository
@Transactional
public class StaffRepoImpl implements StaffRepo {

    EntityManager emanager;

    public StaffRepoImpl(EntityManager emanager) {
        this.emanager = emanager;
    }

    @Override
    public Staff getStaffById(Long id) {
        return emanager.find(Staff.class, id);
    }

    @Override
    public Staff getStaffByUsername(String username) {
        String hql = "FROM Staff s WHERE s.username = :username";
        Query query = emanager.createQuery(hql);
        query.setParameter("username", username);
        return (Staff) query.getSingleResult();
    }

    @Override
    public List<Staff> getAllStaffs() {
        String hql = "FROM Staff";
        Query query = emanager.createQuery(hql);
        return query.getResultList();
    }

    @Override
    public void updateStaffPassword(Long id, String newPassword) {
        Staff staff = getStaffById(id);
        if (staff != null) {
            staff.setPassword(newPassword);
            emanager.merge(staff);
        }
    }

	@Override
	public Staff getStaffByEmail(String email) {
		String hql = "From Staff s WHERE s.email = :email";
        Query query = emanager.createQuery(hql,Staff.class);
        query.setParameter("email", email);
        return (Staff) query.getSingleResult();
	}
	
	 @Override
		public Staff getStaffByOrgEmail(String orgemail) {
			String hql = "From Staff s WHERE s.orgemail = :orgemail";
			Query query = emanager.createQuery(hql);
			query.setParameter("orgemail", orgemail);
			return (Staff) query.getSingleResult();
		}

	@Override
	public Staff updateStaff(Staff staff) {
		Staff existingStaff = emanager.find(Staff.class, staff.getId());
	    if (existingStaff != null) {
	        existingStaff.setPassword(staff.getPassword());
	        existingStaff.setPwdchanged(true);
	        return emanager.merge(existingStaff);
	    }
	    return null;
	 }

	@Override
	public List<Staff> getStaffListByRole(String role) {
		String hql = "From Staff s where s.role = :role";
		Query q = emanager.createQuery(hql);
		q.setParameter("role", role);
		return q.getResultList();
	}
    
}
