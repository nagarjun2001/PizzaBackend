package com.pizza.repoimpl;

import org.springframework.stereotype.Repository;

import com.pizza.model.Admin;
import com.pizza.model.Staff;
import com.pizza.repo.AdminRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.util.List;

@Repository
@Transactional
public class AdminRepoImpl implements AdminRepo {

    EntityManager emanager;

    public AdminRepoImpl(EntityManager emanager) {
        this.emanager = emanager;
    }

    @Override
    public void addAdmin(Admin admin) {
        emanager.persist(admin);
    }

    @Override
    public Admin getAdminById(Long id) {
        return emanager.find(Admin.class, id);
    }

    @Override
    public Admin getAdminByUsername(String username) {
        String hql = "FROM Admin a WHERE a.username = :username";
        Query query = emanager.createQuery(hql);
        query.setParameter("username", username);
        return (Admin) query.getSingleResult();
    }

    @Override
    public List<Admin> getAllAdmins() {
        String hql = "FROM Admin";
        Query query = emanager.createQuery(hql);
        return query.getResultList();
    }

    @Override
    public void addStaff(Staff staff) {
        emanager.persist(staff);
    }

    @Override
    public Staff getStaffById(Long id) {
        return emanager.find(Staff.class, id);
    }

    @Override
    public void deleteStaffById(Long id) {
        Staff staff = getStaffById(id);
        if (staff != null) {
            emanager.remove(staff);
        }
    }
}
